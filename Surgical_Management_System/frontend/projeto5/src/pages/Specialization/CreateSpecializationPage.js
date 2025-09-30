import React, {useState} from 'react';
import '../../app/globals.css';
import Sidebar from "../../Components/Others/SideBar";
import SpecializationForm from "../../Components/Specialization/SpecializationForm";

/**
 * CreateSpecializationPage Component
 * 
 * @component
 * @description A page component that provides a form interface for creating new specializations.
 * Contains a form for inputting specialization details and handles the creation process.
 * 
 * @example
 * return (
 *   <CreateSpecializationPage />
 * )
 */
const CreateSpecializationPage = () => {
    /**
     * @state {boolean} isSuccess - Indicates if the specialization was successfully created
     */
    const [isSuccess, setIsSuccess] = useState(false);

    /**
     * Handles the submission of a new specialization
     * 
     * @async
     * @function handleSubmit
     * @param {Object} data - The specialization data to be created
     * @param {string} data.name - The name of the specialization
     * @param {string} data.code - The unique code for the specialization
     * @param {string} [data.description] - Optional description of the specialization
     * @returns {Promise<void>}
     */
    const handleSubmit = async (data) => {
        try {
            const response = await fetch("http://localhost:4000/api/specialization", {
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
            console.log('Specialization was created:', result);

            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);

        } catch (error) {
            console.error('Error:', error);
        }
    };

    /**
     * Admin information object containing profile details
     * @constant
     * @type {Object}
     * @property {string} fullName - Admin's full name
     * @property {string} email - Admin's email address
     * @property {string} contact - Admin's contact information
     * @property {string} profileImage - Path to admin's profile image
     */
    const adminInfo = {
        fullName: "Admin Name",
        email: "admin@gmail.com",
        contact: "987654321",
        profileImage: '/4792929.png'
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

                <div className="flex-grow flex items-center justify-center">
                    <div className="w-full max-w-lg">
                        <h1 className="text-xl font-semibold mb-4 text-center">Add Specialization</h1>
                        {isSuccess && (
                            <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                Specialization created successfully!
                            </div>
                        )}

                        <SpecializationForm onSubmit={handleSubmit}/>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CreateSpecializationPage;
