"use client";

import React, { useState } from 'react';
import Link from "next/link";
import '../../app/globals.css';
import {useRouter} from "next/router";
import SideBarStaff from "../../Components/Others/SideBarStaff";

export default function Page() {
    const router = useRouter();
    const { email } = router.query as { email: string };
    const [showStaffProfile] = useState(true);
    const fullName = email ? email.split('@')[0] : "Staff";
    
   

    const staffInfo = {
        fullName: fullName,
        email: email,
        contact: "----",
        profileImage: '/4792929.png' // URL da imagem do staff
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
                <SideBarStaff profileImage={staffInfo.profileImage} fullName={staffInfo.fullName} email={staffInfo.email}/>

                

                <div className="flex-1 p-8">
                    
                    {showStaffProfile && (
                        <>
                            {/* Título centralizado */}
                            <h1 className="text-3xl font-bold text-center mb-6">Staff Profile</h1>

                            {/* Container das informações do Staff e imagem, em linha */}
                            <div className="flex items-center justify-between w-full max-w-xl mx-auto mt-40">

                                
                                {/* Informações do Staff no lado esquerdo */}
                                <div className="text-left text-lg text-gray-700 dark:text-gray-200">
                                    <p className="font-semibold">{staffInfo.fullName}</p>
                                    <p>Email: {staffInfo.email}</p>
                                    <p>Contact: {staffInfo.contact}</p>
                                </div>

                                {/* Imagem do Staff no lado direito */}
                                <div
                                    className="w-32 h-32 rounded-full overflow-hidden border-2 border-gray-300 dark:border-gray-600">
                                    <img
                                        src={staffInfo.profileImage}
                                        alt={`${staffInfo.fullName}'s profile`}
                                        className="w-full h-full object-cover"
                                    />
                                    <div className="absolute top-4 right-4">
                                        <Link href="http://localhost:3000/Hospital3d/page">
                                            <button className="bg-pink-500 text-white px-4 py-2 rounded">
                                                Hospital 3d
                                            </button>
                                        </Link>
                                    </div>
                                </div>
                            </div>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
}