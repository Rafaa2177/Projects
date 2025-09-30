import React, { useState } from 'react';
import Sidebar from "../../Components/Others/SideBarStaff";
import { useRouter } from 'next/router';
import '../../app/globals.css';
import AppointmentForm from '@/Components/Appointment/AppointmentForm';

interface Appointment {
    id: string;
    doctorId: string;
    pacientId:string
    patientName: string;
    doctorName: string;
    room: string;
    operationType: string;
    date: string;
    time: string;
    status: string;
}

interface AdminInfo {
    fullName: string;
    email: string;
    contact: string;
    profileImage: string;
}

const ListAppointments: React.FC = () => {
    const [appointments, setAppointments] = useState<Appointment[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);
    const [isSuccess, setIsSuccess] = useState<boolean>(false);
    const router = useRouter();

    const adminInfo: AdminInfo = {
        fullName: "Admin Name",
        email: "admin@example.com",
        contact: "----",
        profileImage: '/4792929.png'
    };

    const handleEditAppointment = (appointment: Appointment): void => {
        // Implementar lógica de edição
        console.log('Editar consulta:', appointment);
    };

    const handleDeleteAppointment = async (id: string): Promise<void> => {
        // Implementar lógica de cancelamento
        console.log('Cancelar consulta:', id);
    };

    const handleSearch = async (): Promise<void> => {
        setLoading(true);
        setError(null);

        try {
            const response = await fetch("http://localhost:4000/api/appointments");

            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }

            const data = await response.json();
            setAppointments(data);
        } catch (err) {
            console.error("Error during fetch:", err);
            setError(err instanceof Error ? err.message : 'An error occurred');
        } finally {
            setLoading(false);
        }
    };

    return (
        
        <div
            className="flex items-center justify-center min-h-screen p-4 sm:p-8 font-[family-name:var(--font-geist-sans)]"
            style={{
                backgroundImage: "url('/4863428.jpg')",
                backgroundSize: "cover",
                backgroundPosition: "center",
            }}
        >
            <div
                className="bg-white dark:bg-gray-800 border border-gray-300 dark:border-gray-700 rounded-lg p-10 w-full max-w-6xl shadow-lg flex"
                style={{
                    minHeight: "90vh",
                }}
            >
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email}/>

                <div className="flex-grow flex flex-col space-y-4 relative">
                    <h1 className="text-3xl font-semibold text-center mt-2">Creating an Appointment</h1>
                    
                    <div className="w-full">
                        <AppointmentForm></AppointmentForm>
                    </div>

                </div>
            </div>
        </div>
    );
};

export default ListAppointments;