import React, { useState, useEffect } from 'react';
import Sidebar from "../../Components/Others/SideBar";
import { useRouter } from 'next/router';
import AllergyUpdateForm from '../../Components/Allergy/AllergyUpdateForm';

const ListAllergyPage = () => {
    const [allergies, setAllergies] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [selectedAllergy, setSelectedAllergy] = useState(null);
    const [isSuccess, setIsSuccess] = useState(false);
    const router = useRouter();

    const adminInfo = {
        fullName: "Admin Name",
        email: "admin@example.com",
        contact: "----",
        profileImage: '/4792929.png'
    };

    const fetchAllergies = async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await fetch("http://localhost:4000/api/allergies/");

            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }

            const data = await response.json();
            setAllergies(data);
        } catch (err) {
            console.error("Error during fetch:", err);
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchAllergies();
    }, []);
    
    
    const fetchAllergyById = async (id) => {
        setLoading(true);
        try {
            const response = await fetch(`http://localhost:4000/api/allergies/${id}`);
            if (!response.ok) {
                throw new Error('Allergy not found.');
            }
            const data = await response.json();
            setSelectedAllergy(data);
        } catch (error) {
            console.error("Error fetching allergy:", error);
            setSelectedAllergy(null);
        } finally {
            setLoading(false);
        }
    };

    const handleEdit = (id) => {
        fetchAllergyById(id);
    };

    const handleUpdateSubmit = async (data) => {
        try {
            // Create a copy of the data object without the id field
            const { id, ...updateData } = data;

            const response = await fetch(`http://localhost:4000/api/allergies/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updateData),
            });

            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(`Update failed: ${errorMessage}`);
            }

            const updatedData = await response.json();
            setSelectedAllergy(updatedData); // Update local state with the response
            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            console.error('Error updating allergy:', error);
            alert(`Failed to update: ${error.message}`);
        }
    };

    useEffect(() => {
        fetchAllergies();
    }, []);

    return (
        <div
            className="flex items-center justify-center min-h-screen p-4 sm:p-8"
            style={{
                backgroundImage: "url('/4863428.jpg')",
                backgroundSize: "cover",
                backgroundPosition: "center",
            }}
        >
            <div
                className="bg-white dark:bg-gray-800 border border-gray-300 dark:border-gray-700 rounded-lg p-10 w-full max-w-6xl shadow-lg flex"
                style={{ minHeight: "90vh" }}
            >
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email} />

                <div className="flex-grow flex flex-col space-y-4 relative">
                    <h1 className="text-3xl font-semibold text-center mt-2">Allergy Management</h1>

                    {selectedAllergy ? (
                        <div className="flex flex-col items-center">
                            <h2 className="text-2xl font-semibold mb-4">Update Allergy</h2>
                            {isSuccess && (
                                <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                    Allergy updated successfully!
                                </div>
                            )}
                            <AllergyUpdateForm
                                onSubmit={handleUpdateSubmit}
                                initialData={selectedAllergy}
                            />
                            <button
                                onClick={() => setSelectedAllergy(null)}
                                className="mt-4 bg-gray-500 text-white py-2 px-4 rounded hover:bg-gray-400"
                            >
                                Back to List
                            </button>
                        </div>
                    ) : (
                        <div className="w-full max-w-3xl bg-white p-6 rounded-lg shadow-md overflow-y-auto" style={{ maxHeight: '800px' }}>
                            {loading ? (
                                <p className="text-gray-500">Loading...</p>
                            ) : allergies.length > 0 ? (
                                allergies.map((allergy, index) => (
                                    <div key={index} className="mb-4 p-4 border-b border-gray-300">
                                        <h2 className="text-lg font-bold text-gray-700 mb-2">Allergy {index + 1}</h2>
                                        <p><strong>Name:</strong> {allergy.name}</p>
                                        <p><strong>ID:</strong> {allergy.id || "No ID"}</p>
                                        <p><strong>Code:</strong> {allergy.code}</p>
                                        <p><strong>Description:</strong> {allergy.description || "No description"}</p>
                                        <div className="mt-3">
                                            <button
                                                onClick={() => handleEdit(allergy.id)}
                                                className="bg-pink-400 text-white py-2 px-4 rounded mr-2 hover:bg-pink-300"
                                            >
                                                Edit
                                            </button>
                                        </div>
                                    </div>
                                ))
                            ) : (
                                <p className="text-gray-500">No allergies found.</p>
                            )}
                        </div>
                    )}

                    {!selectedAllergy && (
                        <div className="absolute bottom-10 right-6">
                            <button
                                onClick={() => fetchAllergies()}
                                className="min-w-32 bg-pink-400 text-white font-semibold py-2 px-4 rounded hover:bg-pink-300"
                            >
                                Refresh List
                            </button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default ListAllergyPage;
