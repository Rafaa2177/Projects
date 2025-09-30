import React, { useState, useEffect } from 'react';
import Sidebar from "../../Components/Others/SideBarStaff";
import { useRouter } from 'next/router';
import '../../app/globals.css';
import EditAppointmentModal from '../../Components/Appointment/EditAppointmentModal';

interface Appointment {
    id: string;
    doctorId: string;
    pacientId: string;
    patientName: string;
    doctorName: string;
    room: string;
    operationType: string;
    startDate: string;
    endDate: string;
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
    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
    const [editingAppointment, setEditingAppointment] = useState<Appointment | null>(null);
    const router = useRouter();

    const adminInfo: AdminInfo = {
        fullName: "Admin Name",
        email: "admin@example.com",
        contact: "----",
        profileImage: '/4792929.png'
    };

    const handleEditAppointment = (appointment: Appointment): void => {
        setEditingAppointment(appointment);
        setIsModalOpen(true);
    };

    const handleDeleteAppointment = async (id: string): Promise<void> => {
        // Implementar lógica de cancelamento
        console.log('Cancelar consulta:', id);
    };

    const fetchPatientNames = async (appointments: Appointment[]) => {
        try {
            let docName = "Nome indisponível";
            try {
                const response = await fetch(`https://localhost:5001/api/staffs/${localStorage.getItem('docId')}`);
                const data = await response.json();
                docName = data.fullName;
            } catch (error) {
                console.error(`Erro ao buscar nome do médico ${localStorage.getItem('docId')}:`, error);
            }
            const updatedAppointments = await Promise.all(
                appointments.map(async (appointment) => {
                    try {
                        const response = await fetch(`https://localhost:5001/api/Patients/recordNumber/${appointment.pacientId}`);
                        const data = await response.json();
                        return { ...appointment, patientName: data.fullName, doctorName: docName };
                    } catch (error) {
                        console.error(`Erro ao buscar nome do paciente ${appointment.pacientId}:`, error);
                        return { ...appointment, patientName: "Nome indisponível" };
                    }
                })
            );
            setAppointments(updatedAppointments);
        } catch (error) {
            console.error("Erro ao buscar nomes dos pacientes:", error);
        }
    };

    useEffect(() => {
        handleSearch();
    }, []);

    const handleSearch = async (): Promise<void> => {
        setLoading(true);
        setError(null);

        try {
            const response = await fetch(`http://localhost:4000/api/appointments/doctor/${localStorage.getItem('docId')}`);

            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }

            const data = await response.json();
            setAppointments(data);
            fetchPatientNames(data);
        } catch (err) {
            console.error("Error during fetch:", err);
            setError(err instanceof Error ? err.message : 'An error occurred');
        } finally {
            setLoading(false);
        }
    };

    const handleSave = async (updatedAppointment: Appointment) => {
        console.log('Saving appointment:', updatedAppointment);
        const { patientName, doctorName, ...appointmentPayload } = updatedAppointment; // Exclude patientName and doctorName
        try {
            const response = await fetch(`http://localhost:4000/api/appointments/`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(appointmentPayload),
            });
            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }
            const result = await response.json();
            console.log('Appointment updated:', result);
        } catch (error) {
            console.error('Error updating appointment:', error);
        }
        setAppointments(prev => prev.map(app => app.id === updatedAppointment.id ? updatedAppointment : app));
        setIsModalOpen(false);
        setIsSuccess(true);
        setTimeout(() => setIsSuccess(false), 3000);
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
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email} />

                <div className="flex-grow flex flex-col space-y-4 relative">
                    <h1 className="text-3xl font-semibold text-center mt-2">Appointments Listing</h1>

                    <div className="w-full p-6 rounded-lg overflow-y-auto h-[62vh] [&::-webkit-scrollbar]:w-2
                                [&::-webkit-scrollbar-track]:bg-gray-100
                                [&::-webkit-scrollbar-track]:rounded-full
                                [&::-webkit-scrollbar-thumb]:bg-gray-300
                                [&::-webkit-scrollbar-thumb]:rounded-full">
                        {loading ? (
                            <p className="text-gray-500">Carregando...</p>
                        ) : appointments.length > 0 ? (
                            appointments.map((appointment, index) => (
                                <div key={index} className="mb-4 p-4 border-b border-gray-300">
                                    <h2 className="text-lg font-bold text-gray-700 mb-2">Consulta {index + 1}</h2>

                                    <p><strong>Paciente:</strong> {appointment.patientName}</p>
                                    <p><strong>Médico:</strong> {appointment.doctorName}</p>
                                    <p><strong>Data Início:</strong> {appointment.startDate}</p>
                                    <p><strong>Data Fim:</strong> {appointment.endDate}</p>
                                    <p><strong>Sala:</strong> {appointment.room}</p>
                                    <p><strong>Status:</strong> {appointment.status}</p>
                                    <div className="mt-2 space-x-2">
                                        <button
                                            onClick={() => handleEditAppointment(appointment)}
                                            className="bg-pink-400 text-white px-4 py-2 rounded hover:bg-pink-300"
                                        >
                                            Editar
                                        </button>
                                    </div>
                                </div>
                            ))
                        ) : (
                            <p className="text-gray-500">Nenhuma consulta encontrada.</p>
                        )}
                    </div>

                    {isSuccess && (
                        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg text-center">
                            Operação realizada com sucesso!
                        </div>
                    )}
                </div>
            </div>
            <EditAppointmentModal
                appointment={editingAppointment}
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSave={handleSave}
            />
        </div>
    );
};

export default ListAppointments;