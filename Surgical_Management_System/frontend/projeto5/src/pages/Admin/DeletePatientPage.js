import React, { useState } from 'react';
import PacientForm from '../../Components/Pacient/PacientForm';
import Link from "next/link";
import Sidebar from "../../Components/Others/SideBar";

const DeletePacientPage = () => {
    const [isSuccess, setIsSuccess] = useState(false);
    const adminInfo = {
        fullName: "Admin Name",
        email: "admin@gmail.com",
        contact: "987654321",
        profileImage: '/4792929.png' // URL da imagem do admin
    };

    const handleSubmit = async (data) => {
        try {
            const confirmation = window.confirm(`Are you sure you want to delete the patient with email: ${data.email}?`);
            if (!confirmation) return;

            const response = await fetch(`https://localhost:5001/api/Patients/email/${data.email}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(await response.text());
            }

            console.log(`Paciente com email ${data.email} apagado.`);
            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            console.error('Erro ao apagar paciente:', error);
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
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} />
                {/* Conteúdo centralizado */}
                <div className="flex flex-col items-center w-full">
                    <h1 className="text-2xl font-bold text-pink-600 mb-4">Delete Patient</h1>
                    <p className="text-lg text-gray-700 mb-6 text-center">
                       
                    </p>

                    {/* Notificação de sucesso */}
                    {isSuccess && (
                        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg text-center">
                            Patient deleted successfully!
                        </div>
                    )}

                    {/* Formulário para apagar patient profile */}
                    <div className="w-full max-w-md">
                        <PacientForm onSubmit={handleSubmit} isDeleteForm={true} />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DeletePacientPage;
