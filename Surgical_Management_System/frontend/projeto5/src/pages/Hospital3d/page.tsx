import React, { useEffect, useRef, CSSProperties } from 'react';
import * as THREE from 'three';
import ThumbRaiser from './thumb_raiser';
import Orientation from './orientation';
import Link from "next/link";
import Camera  from './camera';
interface Appointment{
    doctorId: string,
    doctorName: string,
    pacientId: string,
    pacientName: string,
    startDate: Date,
    endDate: Date,
    room: string,
    operationType: string,
    status: string
}

const ThumbRaiserComponent: React.FC = () => {
    const thumbRaiserRef = useRef<ThumbRaiser | null>(null);
    const [filteredAppointments, setFilteredAppointments] = React.useState<Appointment[]>([]);
    const styles: { [key: string]: CSSProperties } = {
        body: { margin: 0 },
        parent: { position: 'absolute', left: '50vw' },
        viewsPanel: {
            position: 'absolute',
            left: '-50vmin',
            top: '1vh',
            zIndex: 1,
            width: '100vmin',
            textAlign: 'center',
            fontFamily: 'Arial, sans-serif',
            fontSize: '1.5vmin',
            color: 'white',
        },
        helpPanel: {
            position: 'absolute',
            left: '-50vmin',
            top: '20vh',
            zIndex: 1,
            width: '100vmin',
            fontFamily: 'Arial, sans-serif',
            fontSize: '1.5vmin',
            color: 'white',
        },
        subwindowsPanel: {
            position: 'absolute',
            left: '-49vw',
            bottom: '-81vh',
            zIndex: 1,
            width: '100vmin',
            fontFamily: 'Arial, sans-serif',
            fontSize: '1.5vmin',
            color: 'white',
        },
        table: { margin: 'auto', border: '1px solid black' },
        viewsTable: { backgroundColor: '#70707070', textAlign: 'right' },
        helpTable: { width: '50vmin', backgroundColor: '#70707050' },
        subwindowsTable: { position: 'absolute', backgroundColor: '#70707070', textAlign: 'right' },
        th: { overflow: 'hidden', border: '1px solid black' },
        td: { overflow: 'hidden', border: '1px solid black' },
        select: { width: '18ch', fontSize: '1.5vmin' },
        inputSmall: { width: '10ch', fontSize: '1.5vmin' },
        inputLarge: { width: '16ch', fontSize: '1.5vmin' },
    };
    const fetchName = async (id: string, type: 'staffs' | 'Patients/recordNumber'): Promise<string> => {
        const response = await fetch(`https://localhost:5001/api/${type}/${id}`);
        if (!response.ok) {
            throw new Error(`Error fetching ${type} name: ${response.statusText}`);
        }
        const data = await response.json();
        return data.fullName;
    };
    const handleSearch = async (): Promise<void> => {
        try{
            const result = await fetch('http://localhost:4000/api/appointments');
            
            if (!result.ok) {
                throw new Error(`Error: ${result.status} - ${result.statusText}`);
            }
            const data: Appointment[] = await result.json();
            const currentDate = new Date();

            const filteredAppointments: Appointment[] = await Promise.all(
                data.filter((appointment) => {
                    const startDate = new Date(appointment.startDate);
                    const endDate = new Date(appointment.endDate);
                    return currentDate >= startDate && currentDate <= endDate && appointment.status === 'SCHEDULED';
                }).map(async (appointment) => {
                    appointment.pacientName = await fetchName(appointment.pacientId, 'Patients/recordNumber');
                    appointment.doctorName = await fetchName(appointment.doctorId, 'staffs');
                    return appointment;
                })
            );
            console.log(filteredAppointments);
            setFilteredAppointments(filteredAppointments);
        }
        catch(err) {
            console.log(err);
        }
    }
    useEffect(() => {
        document.body.style.margin = '0';
        handleSearch();
    },[])
    useEffect(() => {
        
        

            // Initialize ThumbRaiser after component mounts
            const initializeThumbRaiser = () => {
                
                thumbRaiserRef.current = new ThumbRaiser(
                    {}, // General Parameters
                    {scale: new THREE.Vector3(1.0, 0.5, 1.0)}, // Maze parameters
                    {}, // Player parameters
                    {
                        ambientLight: {intensity: 0.1},
                        pointLight1: {intensity: 50.0, distance: 20.0, position: new THREE.Vector3(-3.5, 10.0, 2.5)},
                        pointLight2: {intensity: 50.0, distance: 20.0, position: new THREE.Vector3(3.5, 10.0, -2.5)},
                    }, // Lights parameters
                    {}, // Fog parameters
                    {view: 'fixed', multipleViewsViewport: new THREE.Vector4(0.0, 1.0, 0.45, 0.5)}, // Fixed view camera parameters
                    {
                        view: 'first-person',
                        multipleViewsViewport: new THREE.Vector4(1.0, 1.0, 0.55, 0.5),
                        initialOrientation: new Orientation(0.0, -10.0),
                        initialDistance: 2.0,
                        distanceMin: 1.0,
                        distanceMax: 4.0,
                    }, // First-person view camera parameters
                    {
                        view: 'third-person',
                        multipleViewsViewport: new THREE.Vector4(0.0, 0.0, 0.55, 0.5),
                        initialOrientation: new Orientation(0.0, -20.0),
                        initialDistance: 2.0,
                        distanceMin: 1.0,
                        distanceMax: 4.0,
                    }, // Third-person view camera parameters
                    {
                        view: 'top',
                        multipleViewsViewport: new THREE.Vector4(1.0, 0.0, 0.45, 0.5),
                        initialOrientation: new Orientation(0.0, -90.0),
                        initialDistance: 4.0,
                        distanceMin: 1.0,
                        distanceMax: 16.0,
                    }, // Top view camera parameters
                    {
                        view: 'mini-map',
                        multipleViewsViewport: new THREE.Vector4(0.99, 0.02, 0.3, 0.3),
                        initialOrientation: new Orientation(180.0, -90.0),
                        initialZoom: 0.64,
                    }, // Mini-map view camera parameters
                    {},
                    filteredAppointments
                );

            };

            const animate = () => {
                if (thumbRaiserRef.current) {
                    thumbRaiserRef.current.update();
                }
                requestAnimationFrame(animate);
            };

            initializeThumbRaiser();
            animate();

            return () => {
                thumbRaiserRef.current?.dispose();
            };
        
    }, [filteredAppointments]);
    

    
    
    return (
        <div id="parent" style={styles.parent}>
            <div id="views-panel" style={styles.viewsPanel}>
                <div className="absolute top-4 right-4">
                    <Link href="http://localhost:3000/Admin/HomePage?email=franciscasteixeira07%40gmail.com">
                        <button className="bg-pink-500 text-white px-4 py-2 rounded">
                            Return
                        </button>
                    </Link>
                </div>
                <table style={{...styles.table, ...styles.viewsTable}}>
                    <tbody>
                    <tr>
                        <td style={{display: 'none'}}>
                            <label>View:</label>
                            <select id="view" style={styles.select}>
                                <option value="fixed">Fixed</option>
                            </select>
                        </td>
                        <td>
                            <label>Orientation (h):</label>
                            <input type="number" id="horizontal" required style={styles.inputSmall}/>
                        </td>
                        <td>
                            <label>Orientation (v):</label>
                            <input type="number" id="vertical" required style={styles.inputSmall}/>
                        </td>
                        <td>
                            <input type="button" id="reset" value="Reset view" style={styles.inputLarge}/>
                        </td>
                    </tr>
                    <tr>
                        <td style={{display: 'none'}}>
                            <label>Projection:</label>
                            <select id="projection" style={styles.select}>
                                <option value="perspective">Perspective</option>
                                <option value="orthographic">Orthographic</option>
                            </select>
                        </td>
                        <td>
                            <label>Distance:</label>
                            <input type="number" id="distance" required style={styles.inputSmall}/>
                        </td>
                        <td>
                            <label>Zoom:</label>
                            <input type="number" id="zoom" required style={styles.inputSmall}/>
                        </td>
                        <td>
                            <input type="button" id="reset-all" value="Reset all views" style={styles.inputLarge}/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div id="help-panel" style={styles.helpPanel}>
                <table style={{...styles.table, ...styles.helpTable}}>
                    <tbody>
                    {/* Add help panel content here */}
                    </tbody>
                </table>
            </div>
            <div id="subwindows-panel" style={styles.subwindowsPanel}>
                <table style={{...styles.table, ...styles.subwindowsTable}}>
                <tbody>
                    <tr>
                        <td>
                            <label>Multiple views:</label>
                            <input type="checkbox" id="multiple-views" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>User interface:</label>
                            <input type="checkbox" id="user-interface" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Mini-map:</label>
                            <input type="checkbox" id="mini-map" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Help:</label>
                            <input type="checkbox" id="help" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Statistics:</label>
                            <input type="checkbox" id="statistics" />
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ThumbRaiserComponent;
