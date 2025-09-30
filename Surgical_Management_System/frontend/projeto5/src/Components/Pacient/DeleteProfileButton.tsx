import { useRouter } from 'next/navigation';

import React, {useState} from "react";



const DeleteProfileButton = () => {
    const router = useRouter();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);
    const [isError, setError] = useState(false);
    async function handleRemove() {
        const shouldRemove = confirm("Are you sure you want to delete?");
        if (shouldRemove) {
            const token = localStorage.getItem('accessToken');
            if (token) {
                try {
                    const response = await fetch(`https://localhost:5001/api/Patients/deletePatientAccount/${token}`, {
                        method: 'DELETE',
                    });

                    if (response.ok) {
                        alert("Account deleted successfully");
                        router.push("http://localhost:3000");
                    } else {
                        // Extrair o corpo do erro detalhado
                        const errorDetails = await response.text();
                        alert(`Error deleting request: ${errorDetails || response.statusText}`);
                    }
                } catch (error) {
                    if (error instanceof Error)
                    alert(`An unexpected error occurred: ${error}`);
                }
            } else {
                alert("No access token found.");
            }
        }
    }
    return (

        <div>
            <button
                className={'bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-full'}
                onClick={handleRemove}>
                Delete Account
            </button>
        </div>
    )
        ;
};

export default DeleteProfileButton;
