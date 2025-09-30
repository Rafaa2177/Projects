import React, {useState} from 'react';
import '../../app/globals.css';
import Sidebar from "../../Components/Others/SideBar";
import AllergyForm from "../../Components/Allergy/AllergyForm";
/**
 * React component for the Create Allergy Page.
 * This page allows users to add a new allergy.
 */
const AddStaffPage = () => {
    /**
     * State to manage the success notification visibility.
     */
    const [isSuccess, setIsSuccess] = useState(false);

    /**
     * Handles the form submission to create a new allergy.
     * @param {Object} data - The allergy data to be submitted.
     */
    const handleSubmit = async (data) => {
        try {
            const response = await fetch("http://localhost:4000/api/allergies", {
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
            console.log('Allergy was created:', result);

            setIsSuccess(true);

            // Hide the notification after 7 seconds
            setTimeout(() => setIsSuccess(false), 7000);

        } catch (error) {
            console.error('Error:', error);
        }
    };

    /**
     * Admin information to be displayed in the sidebar.
     */
    const adminInfo = {
        fullName: "Admin Name",
        email: "admin@gmail.com",
        contact: "987654321",
        profileImage: '/4792929.png' // URL of the admin's image
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

                {/* Form container centered */}
                <div className="flex-grow flex items-center justify-center">
                    <div className="w-full max-w-lg">
                        <h1 className="text-xl font-semibold mb-4 text-center">Add Allergy</h1>
                        {/* Success notification */}
                        {isSuccess && (
                            <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                                Allergy created successfully!
                            </div>
                        )}

                        <AllergyForm onSubmit={handleSubmit}/>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddStaffPage;