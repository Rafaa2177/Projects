import React, { useState } from 'react';
import Sidebar from "../../Components/Others/SideBar";
import ListPacientForm from "../../Components/Pacient/ListPacientForm";
import { useRouter } from 'next/router';

const ListPacientPage = () => {
    const [patient, setPatients] = useState([]);
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

    const handleSearch = async (filters = {}) => {
        setLoading(true);
        setError(null);

        try {
            let response;
            const { filterBy,  email } = filters;

            if (filterBy === 'email' && email) {
                response = await fetch(`https://localhost:5001/api/Patients/email/${email}`);
           
            } else {
                response = await fetch("https://localhost:5001/api/Patients");
            }

            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }

            const data = await response.json();
            setPatients(Array.isArray(data) ? data : [data]);
        } catch (err) {
            console.error("Error during fetch:", err);
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    const deletePatient = async (email) => {
        try {
            const confirmation = window.confirm(`Are you sure you want to delete the patient member with email: ${email}?`);
            if (!confirmation) return;

            const response = await fetch(`https://localhost:5001/api/Patients/email/${email}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(await response.text());
            }

            setPatients(prevPatients => prevPatients.filter(pacientMember => pacientMember.email?.value !== email));

            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            setError(`Error deleting patient: ${error.message}`);  // Alterado aqui
        }
    };

    const handleEditPatient = (pacientMember) => {
        if (pacientMember) {
            router.push(`/Admin/UpdatePatientPage?email=${pacientMember.email}`);
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
                <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email}/>

                <div className="flex-grow flex flex-col space-y-4 relative">
                    <h1 className="text-3xl font-semibold text-center mt-2">List Patients</h1>

                    <div className="w-full max-w-3xl bg-white p-6 rounded-lg shadow-md overflow-y-auto"
                         style={{ maxHeight: '400px' }}>
                        {loading ? (
                            <p className="text-gray-500">Loading...</p>
                        ) : patient.length > 0 ? (
                            patient.map((pacientMember, index) => (
                                <div key={index} className="mb-4 p-4 border-b border-gray-300">
                                    <h2 className="text-lg font-bold text-gray-700 mb-2">Patient {index + 1}</h2>
                                    <p><strong>First Name:</strong> {pacientMember.firstName}</p>
                                    <p><strong>Second Name:</strong> {pacientMember.lastName}</p>
                                    <p><strong>Full Name:</strong> {pacientMember.fullName}</p>
                                    <p><strong>Record
                                        Number:</strong> {pacientMember.recordNumber?.value || "No license number"}</p>
                                    <p><strong>Phone Number:</strong> {pacientMember.phoneNumber}</p>
                                    <p><strong> Date of birth:</strong> {pacientMember.dateOfBirthFormatted}</p>
                                    <p><strong>Email:</strong> {pacientMember.email}</p>
                                    <p><strong>Allergies:</strong> {pacientMember.allergies}</p>
                                    <p><strong>Medical Conditions:</strong> {pacientMember.medicalConditions}</p>
                                    
                                    <div className="flex gap-4 mt-4">
                                        <button
                                            onClick={() => handleEditPatient(pacientMember)}
                                            className="bg-pink-400 text-white py-2 px-4 rounded hover:bg-pink-300"
                                        >
                                            Edit
                                        </button>
                                        <button
                                            onClick={() => deletePatient(pacientMember.email)}
                                            className="bg-pink-700 text-white py-2 px-4 rounded hover:bg-pink-600"
                                        >
                                            Delete
                                        </button>
                                    </div>
                                </div>
                            ))
                        ) : (
                            <p className="text-gray-500">No patient found.</p>
                        )}
                    </div>

                    <div className="absolute bottom-10 right-6">
                        <button
                            onClick={() => handleSearch()}
                            className="w-28 bg-pink-400 text-white font-semibold py-2 px-4 rounded hover:bg-pink-300"
                        >
                            List All Patients
                        </button>
                    </div>

                    {isSuccess && (
                        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg text-center">
                            Patient was deleted successfully.
                        </div>
                    )}
                    <div className="w-full max-w-sm mb-4">
                        <ListPacientForm onSearch={handleSearch}/>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ListPacientPage;
