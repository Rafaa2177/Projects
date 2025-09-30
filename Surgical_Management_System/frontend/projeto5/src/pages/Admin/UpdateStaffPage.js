import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import Sidebar from "../../Components/Others/SideBar";
import UpdateStaffForm from "../../Components/Staff/UpdateStaffForm";
import StaffDto from "../../dtos/StaffDto";

const UpdatePatientPage = () => {
    const router = useRouter();
    const { email } = router.query;
    const [staffData, setStaffData] = useState(null);
    const [isSuccess, setIsSuccess] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const fullName = email ? email.split('@')[0] : "Admin";
    const profileImage = '/4792929.png'; // URL da imagem do admin

    const adminInfo = {
        fullName: fullName,
        email: email,
        contact: "----",
        profileImage: profileImage
    };

    // Função para buscar dados do staff pelo email
    useEffect(() => {
        if (email) {
            const fetchStaff = async () => {
                setIsLoading(true);
                try {
                    const response = await fetch(`https://localhost:5001/api/Staffs/email/${email}`);
                    if (!response.ok) {
                        throw new Error('Staff not found.');
                    }
                    const staff = await response.json();
                    const staffDto =  ({
                        ...StaffDto,
                        id: staff.id,
                        firstName: staff.firstName,
                        lastName: staff.lastName,
                        fullName: staff.fullName,
                        email: staff.email,
                        phoneNumber: staff.phoneNumber,
                        licenseNumber: { value: staff.licenseNumber.value },
                        role: staff.role,
                        specialization: staff.specialization,
                        timeSlots: staff.timeSlots,
                        active: staff.active,
                    }) 
                    setStaffData(staffDto);
                } catch (error) {
                    console.error("Error fetching staff:", error);
                    setStaffData(null);
                } finally {
                    setIsLoading(false);
                }
            };
            fetchStaff();
        }
    }, [email]); 

    // Função para submeter as atualizações
    const handleUpdateSubmit = async (data) => {
        try {
            const response = await fetch(`https://localhost:5001/api/Staffs/email/${email}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error(await response.text());
            }

            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            console.error('Error updating staff:', error);
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
                    <h1 className="text-2xl font-semibold mb-6">Update Staff Profile</h1>

                    {isLoading && <p>Loading...</p>}

                    {isSuccess && (
                        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg">
                            Staff profile updated successfully!
                        </div>
                    )}

                    {staffData && (
                        <UpdateStaffForm
                            onSubmit={handleUpdateSubmit}
                            initialData={staffData}
                        />
                    )}

                    {!isLoading && !staffData && (
                        <p className="text-red-500">Failed to load staff data. Please try again later.</p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default UpdatePatientPage;
