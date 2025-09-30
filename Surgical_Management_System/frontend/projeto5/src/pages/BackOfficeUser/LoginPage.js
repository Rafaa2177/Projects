import React, { useEffect, useState } from 'react';
import '../../app/globals.css';
import LoginForm from "../../Components/BackOffice User/LoginForm";

const LoginPage = () => {
    const [privacyPolicy, setPrivacyPolicy] = useState('');
    const [isSuccess, setIsSuccess] = useState(false);
    const [isError, setError] = useState(false);
    const [isAccountBlockedMessage, setAccountBlockedMessage] = useState(false);
    const [showPrivacyPolicy, setShowPrivacyPolicy] = useState(false);

    const fetchPrivacyPolicy = async () => {
        try {
            const response = await fetch('/privacyPolicy.txt');
            const text = await response.text();
            setPrivacyPolicy(text);
        } catch (error) {
            console.error('Failed to load privacy policy:', error);
        }
    };

    useEffect(() => {
        fetchPrivacyPolicy();
    }, []);

    const handleSubmit = async (data) => {
        try {
            const response = await fetch("https://localhost:5001/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                const result = await response.text();
                const jason = JSON.parse(result);
                if (result.includes("Conta bloqueada")) {
                    setError(false);
                    setIsSuccess(false);
                    setAccountBlockedMessage(true);
                } else {
                    setAccountBlockedMessage(false);
                    setError(false);
                    setIsSuccess(true);
                    console.log(jason.accessToken);
                    localStorage.setItem('accessToken', jason.accessToken);
                    localStorage.setItem('roles', jason.roles);

                    if (result.includes("Admin")) {
                        window.location.href = `/Admin/HomePage?email=${encodeURIComponent(data.email)}`;
                    } else if (result.includes("Patient")) {
                        window.location.href = `/Patient/page?email=${encodeURIComponent(data.email)}`;
                    } else if (result.includes("Doctor")) {
                        window.location.href = `/Staff/Page?email=${encodeURIComponent(data.email)}`;
                    } else {
                        console.log(result);
                    }
                }
            } else {
                const errorText = await response.text();
                console.error('Error:', errorText);
                setAccountBlockedMessage(false);
                setIsSuccess(false);
                setError(true);
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
                <div className="flex-grow flex items-center justify-center">
                    <div className="w-full max-w-lg">
                        <h1 className="text-xl font-semibold mb-4 text-center">Login</h1>
                        {isSuccess && (
                            <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                User logged successfully!
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
                        <LoginForm onSubmit={handleSubmit} />
                        <div className="mt-4 text-center">
                            <button
                                onClick={() => setShowPrivacyPolicy(true)}
                                className="text-blue-500 underline hover:text-blue-700"
                            >
                                Read Privacy Policy
                            </button>
                        </div>
                        {showPrivacyPolicy && (
                            <div
                                className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center"
                                onClick={() => setShowPrivacyPolicy(false)}
                            >
                                <div
                                    className="bg-white p-6 rounded-lg shadow-lg max-w-3xl w-full max-h-[80vh] overflow-y-auto"
                                    onClick={(e) => e.stopPropagation()}
                                >
                                    <h2 className="text-xl font-bold mb-4">Privacy Policy</h2>
                                    <pre className="text-gray-800 whitespace-pre-wrap">{privacyPolicy}</pre>
                                    <button
                                        onClick={() => setShowPrivacyPolicy(false)}
                                        className="mt-4 bg-pink-400 text-white px-4 py-2 rounded hover:bg-pink-300"
                                    >
                                        Close
                                    </button>
                                </div>
                            </div>
                        )}
                        
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
