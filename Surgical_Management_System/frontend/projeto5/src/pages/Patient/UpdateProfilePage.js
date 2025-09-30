"use client";

import React, { useEffect, useState } from 'react';
import PatientProfileForm from '../../Components/Pacient/PatientProfileForm';
import Link from "next/link";
import { useRouter } from 'next/router';
import SideBarPatient from "../../Components/Others/SideBarPatient";

const UpdateProfilePage = () => {
    const router = useRouter();
    const { email } = router.query 
    const [pacientData, setPacientData] = useState(null);
    const [isSuccess, setIsSuccess] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const fullName = email ? email.split('@')[0] : "Patient";
    const profileImage = "/4792929.png"; // URL da imagem do perfil

    // Função para buscar dados do paciente
    useEffect(() => {
        if (email) {
            const fetchPacient = async () => {
                setIsLoading(true);
                try {
                    const response = await fetch(`https://localhost:5001/api/Patients/email/${email}`);
                    if (!response.ok) {
                        throw new Error('Paciente não encontrado.');
                    }
                    const data = await response.json();
                    setPacientData(data); // Armazena os dados do paciente
                } catch (error) {
                    console.error("Erro ao procurar paciente:", error);
                    setPacientData(null);
                } finally {
                    setIsLoading(false);
                }
            };
            fetchPacient();
        }
    }, [email]);

    // Função para submeter as atualizações
    const handleUpdateSubmit = async (data) => {
        try {
            const response = await fetch(`https://localhost:5001/api/Patients/email/${email}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error(await response.text());
            }

            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            console.error('Erro ao atualizar paciente:', error);
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
                <SideBarPatient profileImage={profileImage} fullName={fullName} email={email} />

                {/* Conteúdo Principal */}
                <div className="flex-grow flex flex-col items-center">
                    <h1 className="text-2xl font-semibold mb-6">Update Patient Profile</h1>

                    {isLoading && <p>Loading...</p>}

                    {isSuccess && (
                        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                            Patient profile updated successfully!
                        </div>
                    )}

                    {pacientData && (
                        <PatientProfileForm
                            onSubmit={handleUpdateSubmit}
                            initialData={pacientData}
                        />
                    )}

                    {!isLoading && !pacientData && (
                        <p className="text-red-500">Failed to load patient data. Please try again later.</p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default UpdateProfilePage;
