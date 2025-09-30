import React, { useState, useEffect } from 'react';
import Sidebar from "../../Components/Others/SideBar";
import { useRouter } from 'next/router';
import MedicalConditionUpdateForm from "../../Components/MedicalCondition/MedicalConditionUpdateForm";

const ListMedicalConditionPage = () => {
    const [medicalCondition, setMedicalCondition] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [selectedMedicalCondition, setSelectedMedicalCondition] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);
    const router = useRouter();
    
    const adminInfo = {
        fullName: "Admin Name",
        email: "admin@example.com",
        contact: "----",
        profileImage: '/4792929.png'
    };
    
    const fetchMedicalConditions = async () => {
        setLoading(true);
        setError(null);
        
        
        try {
            const response = await fetch("http://localhost:4000/api/medicalCondition/");
            
            if(!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }
            
            const data = await response.json();
            setMedicalCondition(data);
        }catch(err) {
            console.error("Error during fetch:", err);
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };


    useEffect(() => {
        fetchMedicalConditions();
    }, []);
    
    
    const fetchMedicalConditionById = async (id) => {
        setLoading(true);
        
        try {
            const response = await fetch(`http://localhost:4000/api/medicalCondition/${id}`);
            if(!response.ok) {
                throw new Error('Medical Condition Not Found');
            }
            const data = await response.json();
            setSelectedMedicalCondition(data);
        } catch (error) {
            console.error("Error fetching medical condition:", error);
            setSelectedMedicalCondition(null);
        }finally {
            setLoading(false);
        }
        
    };
    
    const handleEdit = (id) => {
        fetchMedicalConditionById(id);
    }
    
    const handleUpdateSubmit = async (data) => {
        try {
            // Create a copy of the data object without the id field
            const { id, ...updateData } = data;
            
            const response = await fetch(`http://localhost:4000/api/medicalCondition/${id}`, {
                method: "PUT",
                    headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(updateData)
            });
            
            if(!response.ok) {
                const errorMessage = await response.text();
                throw new Error(`Update failed: ${errorMessage}`);
            }
            
            const updatedData = await response.json();
            setSelectedMedicalCondition(updatedData);
            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            console.error('Error updating medical condition:', error);
            alert(`Error updating medical condition: ${error.message}`);
        }
    };

    useEffect(() => {
        fetchMedicalConditions();
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
                style={{minHeight: "90vh"}}
            >
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email}/>

                <div className="flex-grow flex flex-col space-y-4 relative">
                    <h1 className="text-3xl font-semibold text-center mt-2">Medical Condition Management</h1>

                    {selectedMedicalCondition ? (
                        <div className="flex flex-col items-center">
                            <h2 className="text-2xl font-semibold mb-4">Update Medical Condition</h2>
                            {isSuccess && (
                                <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                    Medical Condition updated successfully!
                                </div>
                            )}
                            <MedicalConditionUpdateForm
                                onSubmit={handleUpdateSubmit}
                                initialData={selectedMedicalCondition}
                            />
                            <button
                                onClick={() => setSelectedMedicalCondition(null)}
                                className="mt-4 bg-gray-500 text-white py-2 px-4 rounded hover:bg-gray-400"
                            >
                                Back to List
                            </button>
                        </div>
                    ) : (
                        <div className="w-full max-w-3xl bg-white p-6 rounded-lg shadow-md overflow-y-auto"
                             style={{maxHeight: '800px'}}>
                            {loading ? (
                                <p className="text-gray-500">Loading...</p>
                            ) : medicalCondition.length > 0 ? (
                                medicalCondition.map((medicalCond, index) => (
                                    <div key={index} className="mb-4 p-4 border-b border-gray-300">
                                        <h2 className="text-lg font-bold text-gray-700 mb-2">Medical Condition {index + 1}</h2>
                                        <p><strong>Name:</strong> {medicalCond.name}</p>
                                        <p><strong>ID:</strong> {medicalCond.id || "No ID"}</p>
                                        <p><strong>Code:</strong> {medicalCond.code}</p>
                                        <div className="mt-3">
                                            <button
                                                onClick={() => handleEdit(medicalCond.id)}
                                                className="bg-pink-400 text-white py-2 px-4 rounded mr-2 hover:bg-pink-300"
                                            >
                                                Edit
                                            </button>
                                        </div>
                                    </div>
                                ))
                            ) : (
                                <p className="text-gray-500">No medical condition found.</p>
                            )}
                        </div>
                    )}

                    {!selectedMedicalCondition && (
                        <div className="absolute bottom-10 right-6">
                            <button
                                onClick={() => fetchMedicalConditions()}
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

export default ListMedicalConditionPage;