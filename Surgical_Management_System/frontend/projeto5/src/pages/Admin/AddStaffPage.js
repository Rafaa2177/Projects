import React, {useState} from 'react';
import StaffForm from "../../Components/Staff/StaffForm";
import Link from "next/link";
import '../../app/globals.css';
import Sidebar from "../../Components/Others/SideBar";
import BackOfficeForm from "../../Components/BackOffice User/BackOfficeForm";

const AddStaffPage = () => {

    const [isSuccess, setIsSuccess] = useState(false);
    const handleSubmit = async (data) => {
        try {
            const response = await fetch("https://localhost:5001/api/Staffs", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error(await response.text());
            }

            const result = await response.json();
            console.log('Staff was created:', result);
            
            setIsSuccess(true);

            // Oculta a notificação após alguns segundos 
            setTimeout(() => setIsSuccess(false), 7000);
            
        } catch (error) {
            console.error('Error:', error);
        }
    };

    const adminInfo = {
        fullName: "Admin Name",
        email: "admin@gmail.com",
        contact: "987654321",
        profileImage: '/4792929.png' // URL da imagem do admin
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

                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName}/>

                {/* Form container centralizado */}
                <div className="flex-grow flex items-center justify-center">
                    <div className="w-full max-w-lg">
                        <h1 className="text-xl font-semibold mb-4 text-center">Add Staff Profile</h1>
                        {/* Notificação de sucesso */}
                        {isSuccess && (
                            <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                Staff Profile created successfully!
                            </div>
                        )}

                        <StaffForm onSubmit={handleSubmit}/>

                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddStaffPage;
