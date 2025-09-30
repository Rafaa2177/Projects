import React, { useState } from 'react';
import OperationRequestDto from "../../dtos/OperationRequestDto";
import SidebarStaff from "../../Components/Others/SideBarStaff";
import OperationForm from "../../Components/OperationRequests/OperationForm";
import {useRouter} from "next/router";

const OperationRequestPage = () => {
    const [isSuccess, setIsSuccess] = useState(false); // Estado para controlar a exibição da notificação
    const router = useRouter();
    const { email } = router.query;
    const fullName = email ? email.split('@')[0] : "Doctor";

    const handleSubmit = async (data) => {
        try {
            console.log(data.OperationDate);
            const formattedDate = new Date(data.OperationDate).toISOString();
            const dto = { ...OperationRequestDto, ...data, operationDate: formattedDate };            
            const response = await fetch("https://localhost:5001/api/OperationRequest", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dto),
            });

            if (!response.ok) {
                throw new Error(await response.text());
            }

            const result = await response.json();
            console.log('Operation Request criado:', result);

            // Atualiza o estado para exibir a notificação de sucesso
            setIsSuccess(true);

            // Oculta a notificação após alguns segundos 
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    const adminInfo = {
        fullName: fullName,
        email: email,
        contact: "-----",
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
                <SidebarStaff profileImage={adminInfo.profileImage} fullName={adminInfo.fullName}  email={adminInfo.email}/>
                {/* Form container centralizado */}
                
                <div className="flex-grow flex items-center justify-center">
                    <div className="w-full max-w-lg">
                        <h1 className="text-xl font-semibold mb-4 text-center">Create Operation Request</h1>
                        {/* Notificação de sucesso */}
                        {isSuccess && (
                            <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                Operation Request created successfully!
                            </div>
                        )}

                        <OperationForm onSubmit={handleSubmit} />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default OperationRequestPage;
