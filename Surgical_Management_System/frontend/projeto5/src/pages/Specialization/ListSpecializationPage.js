import React, { useState, useEffect } from 'react';
import Sidebar from "../../Components/Others/SideBar";
import { useRouter } from 'next/router';

/**
 * ListSpecializationPage Component
 *
 * @component
 * @description A page component that lists all specializations.
 * It fetches the specialization data from the server and displays it in a list format.
 * Provides options to edit or delete each specialization.
 *
 * @example
 * return (
 *   <ListSpecializationPage />
 * )
 */
const ListSpecializationPage = () => {
    const [specialization, setSpecialization] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const router = useRouter();
    const [isSuccess, setIsSuccess] = useState(false);

    
    const adminInfo = {
        fullName: "Admin Name",
        email: "admin@example.com",
        contact: "----",
        profileImage: '/4792929.png'
    };

    /**
     * Fetches the list of specializations from the server
     *
     * @async
     * @function fetchSpecialization
     * @returns {Promise<void>}
     */
    const fetchSpecialization = async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await fetch("http://localhost:4000/api/specialization/");

            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }

            const data = await response.json();
            setSpecialization(data);
        } catch (err) {
            console.error("Error during fetch:", err);
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchSpecialization();
    }, []);

    /**
     * Handles the edit action for a specialization
     *
     * @function handleEdit
     * @param {string} id - The ID of the specialization to edit
     */
    const handleEdit = (id) => {
        router.push(`/Specialization/${id}`);
    };

    /**
     * Handles the delete action for a specialization
     *
     * @function handleDelete
     * @param {string} id - The ID of the specialization to delete
     */
    const handleDelete = (id) => {
        console.log(`Delete specialization with id: ${id}`);
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
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email}/>

                <div className="flex-grow flex flex-col space-y-4 relative">
                    <h1 className="text-3xl font-semibold text-center mt-2">List Specializations</h1>

                    <div className="w-full max-w-3xl bg-white p-6 rounded-lg shadow-md overflow-y-auto"
                         style={{ maxHeight: '800px' }}>
                        {loading ? (
                            <p className="text-gray-500">Loading...</p>
                        ) : specialization.length > 0 ? (
                            specialization.map((specialization, index) => (
                                <div key={index} className="mb-4 p-4 border-b border-gray-300">
                                    <h2 className="text-lg font-bold text-gray-700 mb-2">Specialization {index + 1}</h2>
                                    <p><strong>Name:</strong> {specialization.name}</p>
                                    <p><strong>Id:</strong> {specialization.id || "No id"}</p>
                                    <p><strong>Code:</strong> {specialization.code}</p>
                                    <p><strong>Description:</strong> {specialization.description || "No description"}</p>
                                    <div className="mt-3">
                                        <button
                                            onClick={() => handleEdit(specialization.id)}
                                            className="bg-pink-400 text-white py-2 px-4 rounded mr-2 hover:bg-pink-300"
                                        >
                                            Edit
                                        </button>
                                        <button
                                            onClick={() => handleDelete(specialization.id)}
                                            className="bg-pink-700 text-white py-2 px-4 rounded hover:bg-pink-600"
                                        >
                                            Delete
                                        </button>
                                    </div>
                                </div>
                            ))
                        ) : (
                            <p className="text-gray-500">No specialization found.</p>
                        )}
                    </div>

                    <div className="absolute bottom-10 right-6">
                        <button
                            onClick={() => fetchSpecialization()}
                            className="min-w-32 bg-pink-400 text-white font-semibold py-2 px-4 rounded hover:bg-pink-300"
                        >
                            Refresh List
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ListSpecializationPage;