import React, { useEffect, useState } from 'react';
import SpecializationUpdateForm from '../../Components/Specialization/SpecializationUpdateForm';
import { useRouter } from 'next/router';
import Sidebar from "../../Components/Others/SideBar";

/**
 * Specialization Update Page Component
 * 
 * @component
 * @description A page component that handles the updating of a specialization.
 * It fetches the existing specialization data and provides a form for editing.
 * Includes success notifications and error handling.
 * 
 * @example
 * // Accessed via route: /Specialization/[id]
 * return (
 *   <Id />
 * )
 */
const Id = () => {
    /**
     * @state {Object|null} specializationData - Current specialization data
     * @state {boolean} isSuccess - Indicates if update was successful
     * @state {boolean} isLoading - Indicates if data is being fetched
     */
    const router = useRouter();
    const { id } = router.query;
    const [specializationData, setSpecializationData] = useState(null);
    const [isSuccess, setIsSuccess] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    /**
     * Admin information object containing profile details
     * @constant
     * @type {Object}
     */
    const adminInfo = {
        fullName: "Admin",
        email: "admin@example.com",
        contact: "----",
        profileImage: '/4792929.png'
    };

    /**
     * Fetches specialization data when component mounts or ID changes
     * 
     * @effect
     * @dependency {string} id - Specialization ID from URL
     */
    useEffect(() => {
        if (id) {
            const fetchSpecialization = async () => {
                setIsLoading(true);
                try {
                    const response = await fetch(`http://localhost:4000/api/specialization/${id}`);
                    if (!response.ok) {
                        throw new Error('Especialização não encontrada.');
                    }
                    const data = await response.json();
                    console.log('Fetched specialization data:', data);
                    setSpecializationData(data);
                } catch (error) {
                    console.error("Erro ao buscar especialização:", error);
                    setSpecializationData(null);
                } finally {
                    setIsLoading(false);
                }
            };
            fetchSpecialization();
        }
    }, [id]);

    /**
     * Handles the submission of updated specialization data
     * 
     * @async
     * @function handleUpdateSubmit
     * @param {Object} data - The updated specialization data
     * @param {string} data.id - Specialization ID
     * @param {string} data.name - Specialization name
     * @param {string} data.code - Specialization code
     * @param {string} [data.description] - Optional specialization description
     * @returns {Promise<void>}
     */
    const handleUpdateSubmit = async (data) => {
        try {
            const { id, ...dataWithoutId } = data;
            console.log('Frontend - Sending update request:', {
                id,
                updateData: dataWithoutId
            });

            const response = await fetch(`http://localhost:4000/api/specialization/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dataWithoutId),
            });

            const responseText = await response.text();
            console.log('Frontend - Raw response:', responseText);

            if (!response.ok) {
                throw new Error(`Update failed: ${responseText}`);
            }

            let responseData;
            try {
                responseData = JSON.parse(responseText);
            } catch (e) {
                console.error('Frontend - Failed to parse response:', e);
                throw new Error('Invalid response format');
            }

            console.log('Frontend - Update successful:', responseData);

            const response2 = await fetch(`http://localhost:4000/api/specialization/${id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            const responseText2 = await response2.text();
            console.log('Frontend - Did it update?:', responseText2);

            setSpecializationData(responseData);
            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);

        } catch (error) {
            console.error('Frontend - Update error:', error);
            alert(`Failed to update: ${error.message}`);
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
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email} />

                <div className="flex-grow flex flex-col items-center">
                    <h1 className="text-2xl font-semibold mb-6">Update Specialization</h1>

                    {isLoading && <p>Loading...</p>}

                    {isSuccess && (
                        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                            Specialization updated successfully!
                        </div>
                    )}

                    {specializationData && (
                        <SpecializationUpdateForm
                            onSubmit={handleUpdateSubmit}
                            initialData={specializationData}
                        />
                    )}

                    {!isLoading && !specializationData && (
                        <p className="text-red-500">Failed to load specialization data. Please try again later.</p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Id;