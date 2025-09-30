"use client";

import React, { useState } from 'react';
import Link from "next/link";
import '../../app/globals.css';
import {router} from "next/client";
import {useRouter} from "next/router";
import SideBarPatient from "../../components/Others/SideBarPatient";
import DeleteProfileButton from "../../components/Pacient/DeleteProfileButton";

export default function Page() {
    const router = useRouter();
    const [showAdminProfile, setShowAdminProfile] = useState(true);
    const { email } = router.query as { email: string };
    const fullName = email ? email.split('@')[0] : "Patient";
    
    const hideAdminProfile = () => {
        setShowAdminProfile(false);
    };

    const patientInfo = {
        fullName: fullName,
        email: email,
        profileImage: '/4792929.png' 
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
                <SideBarPatient profileImage={patientInfo.profileImage} fullName={patientInfo.fullName} email={patientInfo.email} />


                <div className="flex-1 p-8">
                    {showAdminProfile && (
                        <div>
                            {/* Título centralizado */}
                            <h1 className="text-3xl font-bold text-center mb-6">Patient Profile</h1>

                            {/* Container das informações do Admin e imagem, em linha */}
                            <div className="flex items-center justify-between w-full max-w-xl mx-auto mt-40">

                                {/* Informações do Admin no lado esquerdo */}
                                <div className="text-left text-lg text-gray-700 dark:text-gray-200">
                                    <p className="font-semibold">{patientInfo.fullName}</p>
                                    <p>Email: {patientInfo.email}</p>
                                </div>

                                {/* Imagem do Admin no lado direito */}
                                <div
                                    className="w-32 h-32 rounded-full overflow-hidden border-2 border-gray-300 dark:border-gray-600">
                                    <img
                                        src={patientInfo.profileImage}
                                        alt={`${patientInfo.fullName}'s profile`}
                                        className="w-full h-full object-cover"
                                    />
                                </div>
                            </div>
                            <div>
                                <DeleteProfileButton/>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}
