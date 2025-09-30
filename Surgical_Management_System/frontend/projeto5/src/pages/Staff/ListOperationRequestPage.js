import React, { useState, useEffect } from 'react';
import SidebarStaff from "../../Components/Others/SideBarStaff";
import { useRouter } from 'next/router';
import OperationRequestDto from "../../dtos/OperationRequestDto";
import PatientDto from '../../dtos/PatientDto';
import DoctorDto from '../../dtos/DoctorDto';
import DeleteOperationRequestButton from "../../components/OperationRequests/DeleteOperationRequestButton";
import '../../app/globals.css';
import EditOperationRequestModal from "../../components/OperationRequests/EditOperationRequestModal";
import SuccessModal from "../../components/Others/SuccessModal";


const operationRequestsInitial = [
    {
        id: '1',
        doctorId: 'D001',
        patientId: 'P001',
        operationType: 'Surgery',
        operationDate: '2024-11-22',
        priority: 'High',
    },
    {
        id: '2',
        doctorId: 'D002',
        patientId: 'P002',
        operationType: 'Checkup',
        operationDate: '2024-12-01',
        priority: 'Medium',
    },
];
const ListOperationRequestPage= () => {
    const getInitialEmail = () => {
        if (typeof window !== "undefined") {
            return localStorage.getItem('email') || ""; // Return the stored email or an empty string
        }
        return ""; // Fallback for SSR
    };
    const router = useRouter();
    const email = getInitialEmail();
    const [fullName, setFullName] = useState("");
    const [selectedOperationRequest, setSelectedOperationRequest] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [filterType, setFilterType] = useState('doctorId');
    const [filterValue, setFilterValue] = useState('');
    const [patients, setPatients] = useState([]);
    const [doctors, setDoctors] = useState([]);
    const [operationRequests, setOperationRequests] = useState(operationRequestsInitial);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [editingRequest, setEditingRequest] = useState(null);
    const [isSuccessModalOpen, setIsSuccessModalOpen] = useState(false);
    const openModal = (request) => {
        setEditingRequest(request);
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setEditingRequest(null);
        setIsModalOpen(false);
    };

    const getUserData = async () => {
        try {
            const response = await fetch(`https://localhost:5001/api/Staffs/email/${email}`, { method: "GET" });
            if (!response.ok) {
                throw new Error("Failed to fetch user data");
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error("Error fetching user data:", error);
            return null;
        }
    };
    if (!email) {
        console.log("Email not found", email);
        console.log("Admin email", email);
    }

    const handleSave = async (updatedRequest) => {
        try {
            const response = await fetch(
                `https://localhost:5001/api/OperationRequest/${localStorage.getItem('accessToken')}/${localStorage.getItem('docId')}`,
                {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(updatedRequest),
                }
            );
            if (!response.ok) {
                throw new Error('Failed to update operation request');
            }
            setOperationRequests((prev) =>
                prev.map((req) => (req.id === updatedRequest.id ? updatedRequest : req))
            );
            closeModal();
            setIsSuccessModalOpen(true);
        } catch (error) {
            console.error(error);
        }
    };
    useEffect(() => {
        if (email) {
            (async () => {
                const userData = await getUserData();
                if (userData) {
                    setFullName(userData.fullName || "Unknown User");
                    localStorage.setItem('docId',userData.licenseNumber.value);
                }
            })();
        }
    }, [email]);
    const adminInfo = {
        fullName: fullName,
        email: email,
        contact: "----",
        profileImage: "/4792929.png"
    };

    useEffect(() => {
        
        const fetchAllOperationRequests = async () => {
            setLoading(true);
            setError(null);
            try {
                
                const response = await fetch("https://localhost:5001/api/OperationRequest");
                const data = await response.json();
                const dtos = data.map(item => ({ ...OperationRequestDto, ...item }));
                setOperationRequests(dtos);
            } catch (error) {
                setError("Failed to fetch operation requests");
            } finally {
                setLoading(false);
            }
        };

        const fetchPatients = async () => {
            try {
                const response = await fetch('https://localhost:5001/api/Patients');
                if (!response.ok) {
                    throw new Error('Failed to fetch patients');
                }
                const data = await response.json();
                const patientDTOs = data.map(patient => ({
                    ...PatientDto,
                    id: patient.id,
                    firstName: patient.firstName,
                    lastName: patient.lastName,
                    fullName: patient.fullName,
                    dateOfBirth: patient.dateOfBirth,
                    dateOfBirthFormatted: patient.dateOfBirthFormatted,
                    email: patient.email,
                    phoneNumber: patient.phoneNumber,
                    emergencyContact: patient.emergencyContact,
                    gender: patient.gender,
                    medicalConditions: patient.medicalConditions,
                    appointments: patient.appointments,
                    recordNumber: patient.recordNumber
                }));
                setPatients(patientDTOs);
            } catch (error) {
                console.error('Error fetching patients:', error);
            }
        };

        const fetchDoctors = async () => {
            try {
                const response = await fetch('https://localhost:5001/api/Staffs/active');
                if (!response.ok) {
                    throw new Error('Failed to fetch doctors');
                }
                const data = await response.json();
                const doctorDTOs = data.map(doctor => ({
                    ...DoctorDto,
                    id: doctor.id,
                    firstName: doctor.firstName,
                    lastName: doctor.lastName,
                    fullName: doctor.fullName,
                    email: doctor.email,
                    phoneNumber: doctor.phoneNumber,
                    licenseNumber: { value: doctor.licenseNumber.value },
                    role: doctor.role,
                    specialization: doctor.specialization,
                    timeSlots: doctor.timeSlots
                }));
                setDoctors(doctorDTOs);
            } catch (error) {
                console.error('Error fetching doctors:', error);
            }
        };
        fetchAllOperationRequests();
        fetchPatients();
        fetchDoctors();
    }, []);

    const handleSearchOperationRequest = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await fetch(`https://localhost:5001/api/OperationRequest/${filterType}/${filterValue}`);
            const data = await response.json();
            const dtos = data.map(item => ({ ...OperationRequestDto, ...item }));
            setOperationRequests(dtos); // Atualiza a lista de operationRequests
            setSelectedOperationRequest(null); // Limpa a seleção anterior
        } catch (error) {
            setError("Operation requests not found");
            setOperationRequests([]); // Limpa a lista em caso de erro
        } finally {
            setLoading(false);
        }
    };
    
    const handleEditOperationRequest = () => {
        if (selectedOperationRequest) {
            router.push(`/Admin/UpdateOperationRequestPage?id=${selectedOperationRequest.id}`);
        }
    };

    const handleDeleteOperationRequest = () => {
        if (selectedOperationRequest) {
            router.push(`/Admin/DeleteOperationRequestPage?id=${selectedOperationRequest.id}`);
        }
    };

    const handleBackToList = () => {
        setSelectedOperationRequest(null);
        setFilterValue('');
        setError(null);
    };
    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p className="text-red-500">{error}</p>;
    }
    return (
        <div className="flex items-center justify-center min-h-screen p-4 sm:p-8">
            <div className="bg-white border border-gray-300 rounded-lg p-10 w-full max-w-6xl shadow-lg flex">
                <SidebarStaff profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email} />

                <div className="w-full">
                    {!selectedOperationRequest && (
                        <div className="bg-white p-6 rounded-lg shadow-md mb-6">
                            <h2 className="text-xl font-bold mb-4">Filter by</h2>
                            <select
                                name="filterType"
                                value={filterType}
                                onChange={(e) => setFilterType(e.target.value)}
                                className="w-full px-4 py-2 border rounded-md mb-4"
                            >
                                <option value="doctorId">Doctor ID</option>
                                <option value="patientId">Patient ID</option>
                                <option value="operationType">Operation Type</option>
                                <option value="priority">Priority</option>
                            </select>
                            {filterType === 'operationType' || filterType === 'priority' ? (
                                <select
                                    name="filterValue"
                                    value={filterValue}
                                    onChange={(e) => setFilterValue(e.target.value)}
                                    className="w-full px-4 py-2 border rounded-md mb-4"
                                >
                                    {filterType === 'operationType' ? (
                                        <>
                                            <option value="Cardiology">Cardiology</option>
                                            <option value="Neurology">Neurology</option>
                                            <option value="Orthopedics">Orthopedics</option>
                                            <option value="Gastroenterology">Gastroenterology</option>
                                            <option value="Pulmonology">Pulmonology</option>
                                            <option value="Oncology">Oncology</option>
                                            <option value="Endocrinology">Endocrinology</option>
                                            <option value="Pediatrics">Pediatrics</option>
                                            <option value="Dermatology">Dermatology</option>
                                            <option value="Urology">Urology</option>
                                            <option value="Gynecology/Obstetrics">Gynecology/Obstetrics</option>
                                            <option value="Ophthalmology">Ophthalmology</option>
                                            <option value="Psychiatry">Psychiatry</option>
                                            <option value="General Surgery">General Surgery</option>
                                            <option value="Radiology">Radiology</option>
                                            <option value="Emergency Medicine">Emergency Medicine</option>
                                        </>
                                    ) : (
                                        <>
                                            <option value="Very High">Very High</option>
                                            <option value="High">High</option>
                                            <option value="Medium">Medium</option>
                                            <option value="Low">Low</option>
                                        </>
                                    )}
                                </select>
                            ) : filterType === 'doctorId' ? (
                                <select
                                    name="filterValue"
                                    value={filterValue}
                                    onChange={(e) => setFilterValue(e.target.value)}
                                    className="w-full px-4 py-2 border rounded-md mb-4"
                                >
                                    <option value="" disabled>Select Doctor</option>
                                    {doctors.map(doctor => (
                                        <option key={doctor.id} value={doctor.id}>
                                            {doctor.licenseNumber.value}
                                        </option>
                                    ))}
                                </select>
                            ) : (
                                <select
                                    name="filterValue"
                                    value={filterValue}
                                    onChange={(e) => setFilterValue(e.target.value)}
                                    className="w-full px-4 py-2 border rounded-md mb-4"
                                >
                                    <option value="" disabled>Select Patient</option>
                                    {patients.map(patient => (
                                        <option key={patient.id} value={patient.id}>
                                            {patient.recordNumber.value}
                                        </option>
                                    ))}
                                </select>
                            )}
                            
                            <button
                                onClick={handleSearchOperationRequest}
                                className="bg-pink-400 text-white py-4 px-10 rounded-lg"
                            >
                                Search
                            </button>
                        </div>
                    )}

                    {!selectedOperationRequest && (
                        <div>
                            {loading ? (
                                <p>Loading...</p>
                            ) : error ? (
                                <p className="text-red-500">{error}</p>
                            ) : (
                                
                                <ul>
                                    {operationRequests.length === 0 ? (
                                        <p style={{ marginLeft: '20px' }}>No operation requests available.</p>
                                    ) : (
                                        operationRequests.map((request) => (
                                            <li key={request.id} className="bg-white p-6 rounded-lg shadow-md mt-4">
                                                <p><strong>Doctor ID:</strong> {request.doctorId}</p>
                                                <p><strong>Patient ID:</strong> {request.patientId}</p>
                                                <p><strong>Operation Type:</strong> {request.operationType}</p>
                                                <p><strong>Operation Date:</strong> {request.operationDate}</p>
                                                <p><strong>Priority:</strong> {request.priority}</p>
                                                {console.log(request)}
                                                <DeleteOperationRequestButton id={request.id}/>
                                                <button
                                                    onClick={() => openModal(request)}
                                                    className="bg-pink-400 text-white py-2 px-5 rounded-lg float-right -mt-8"
                                                >
                                                    Editar
                                                </button>

                                            </li>
                                        ))
                                    )}
                                </ul>
                            )}
                            <EditOperationRequestModal
                                operationRequest={editingRequest}
                                isOpen={isModalOpen}
                                onClose={closeModal}
                                onSave={handleSave}
                            />
                            <SuccessModal
                                isOpen={isSuccessModalOpen}
                                onClose={() => setIsSuccessModalOpen(false)}
                                message="Operation request updated successfully!"
                            />
                        </div>
                    )}

                    {selectedOperationRequest && (
                        <div className="w-full bg-white p-6 rounded-lg shadow-md mt-6">
                            <h2 className="text-xl font-bold mb-4">Details</h2>
                            <p><strong>Doctor ID:</strong> {selectedOperationRequest.doctorId}</p>
                            <p><strong>Patient ID:</strong> {selectedOperationRequest.patientId}</p>
                            <p><strong>Operation Type:</strong> {selectedOperationRequest.operationType}</p>
                            <p><strong>Operation Date:</strong> {selectedOperationRequest.operationDate}</p>
                            <p><strong>Priority:</strong> {selectedOperationRequest.priority}</p>

                            <div className="mt-4">
                                <button onClick={() => openModal(selectedOperationRequest)} className="bg-pink-400 text-white px-4 py-2 mr-4">
                                    Edit Operation Request
                                </button>
                                <button onClick={handleDeleteOperationRequest} className="bg-pink-400 text-white px-4 py-2 mr-4">
                                    Delete Operation Request
                                </button>
                                <button onClick={handleBackToList} className="bg-gray-300 text-gray-700 px-4 py-2">
                                    Back
                                </button>
                            </div>
                            
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default ListOperationRequestPage;