import React, {useState} from 'react';
import '../../app/globals.css';
import RegisterForm from "@components/BackOffice User/RegisterForm";
import { RegisterFormData } from '@/utils/interfaces';
const LoginPage = () => {
    const [isSuccess, setIsSuccess] = useState(false);
    const [isError, setError] = useState(false);
    const [isAccountBlockedMessage, setAccountBlockedMessage] = useState(false);
    

    const handleSubmit = async (data:RegisterFormData) => {
        try {
            const response = await fetch("https://localhost:5001/RegisterPatient", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });


            if (response.ok) {
                const result = await response.text();
                if (result.includes("Conta bloqueada")) {
                    setError(false);
                    setIsSuccess(false);
                    setAccountBlockedMessage(true);

                } else {
                    setAccountBlockedMessage(false);
                    setError(false)
                    // Update the state to show the success notification
                    setIsSuccess(true);
                    if (result.includes("Admin")) {
                        window.location.href = `/Admin/HomePage?email=${encodeURIComponent(data.email)}`;
                    }
                    else{
                        console.log(result)
                    }
                }
            } if (!response.ok) {
                const errorText = await response.text();
                console.error('Error:', errorText);
                setAccountBlockedMessage(false);
                setIsSuccess(false);
                setError(true); // Ensure success message is not shown on error
            }

        } catch (error) {
            console.error('Error:', error);
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



                {/* Form container centralizado */}
                <div className="flex-grow flex items-center justify-center">
                    <div className="w-full max-w-lg">
                        <h1 className="text-xl font-semibold mb-4 text-center">Register for HealthCare app</h1>
                        {/* Notificação de sucesso */}
                        {isSuccess && (
                            <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                User registered successfully!
                            </div>
                        )}
                        {isError && (
                            <div className="mb-4 p-4 bg-red-200 text-red-800 rounded-lg">
                                Wrong password or email, try again!
                            </div>
                        )}
                        {isAccountBlockedMessage && (
                            <div className="mb-4 p-4 bg-yellow-200 text-yellow-800 rounded-lg">
                                Account blocked, check your registered email.
                            </div>
                        )}
                        <RegisterForm onSubmit={handleSubmit}/>

                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
