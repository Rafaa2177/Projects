import React, {useEffect, useState} from 'react';
import Link from 'next/link';

interface SidebarProps {
    profileImage: string;
    fullName: string;
    email: string;
}

const Sidebar: React.FC<SidebarProps> = ({ profileImage}) => {


    const [fullName, setFullName] = useState("");
    const getInitialEmail = () => {
        if (typeof window !== "undefined") {
            return localStorage.getItem('email') || ""; // Return the stored email or an empty string
        }
        return ""; // Fallback for SSR
    };

    const email = getInitialEmail();
    console.log("Email:", email);
    const getUserData = async () => {
        try {
            const response = await fetch(`https://localhost:5001/api/Staffs/email/${email}`, { method: "GET" });
            if (!response.ok) {
                console.log("Response not ok", response);
                throw new Error("Failed to fetch user data");
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error("Error fetching user data:", error);
            return null;
        }
    };
    if (!email) {
        console.log("Email not found", email);
        console.log("Admin email", email);
    }

    useEffect(() => {
        if (email) {
            (async () => {
                const userData = await getUserData();
                if (userData) {
                    setFullName(userData.fullName || "Unknown User");
                    localStorage.setItem('docId', userData.licenseNumber.value);
                }
            })();
        }
    }, [email]);




    return (
        <div className="w-64 bg-white dark:bg-gray-800 border-r border-gray-300 dark:border-gray-700 flex flex-col items-start p-4">
            <Link href={`/Admin/HomePage?email=${email}`}>
                <div className="flex items-center w-full mb-4 p-2 bg-gray-100 dark:bg-gray-700 rounded-lg cursor-pointer">
                    <img
                        src={profileImage}
                        alt={`${fullName}'s profile`}
                        className="w-12 h-12 rounded-full border-2 border-gray-300 dark:border-gray-600"
                    />
                    <div className="ml-3">
                        <p className="text-sm font-semibold text-gray-800 dark:text-gray-100">
                            {fullName}
                        </p>
                    </div>
                </div>
            </Link>

            <h2 className="text-xl font-semibold mb-4 text-left">Staffs</h2>
            <nav className="flex flex-col gap-4">
                <Link href={`/Admin/AddStaffPage?email=${email}`} className="text-black hover:underline">
                    Add Staff Profile
                </Link>
                <Link href={`/Admin/ListStaffPage?email=${email}`} className="text-black hover:underline">
                    List Staff Profiles
                </Link>
                <Link href={`/Admin/InactivateStaffPage?email=${email}`}
                      className="text-left text-black hover:underline">
                    Deactivate Staff Profile
                </Link>
                <h2 className="text-xl font-semibold mb-4 mt-6 text-left">Patients</h2>
                <Link href={`/Admin/AddPacientPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Add Patient Profile
                </Link>
                <Link href={`/Admin/ListPacientPage?email=${email}`} className="text-left text-black hover:underline">
                    List Patients Profile
                </Link>

                <h2 className="text-xl font-semibold mb-4 mt-6 text-left">BackOffice User</h2>
                <Link href={`/Admin/CreateBackOfficeUserPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Create BackOffice User
                </Link>
                <h2 className="text-xl font-semibold mb-4 mt-6 text-left">Allergies</h2>
                <Link href={`/Allergy/CreateAllergyPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Add Allergy
                </Link>
                <Link href={`/Allergy/ListAllergyPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    List Allergies
                </Link>

                <h2 className="text-xl font-semibold mb-4 mt-6 text-left">Specializations</h2>
                <Link href={`/Specialization/CreateSpecializationPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Add Specialization
                </Link>
                <Link href={`/Specialization/ListSpecializationPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    List Specializations
                </Link>

                <h2 className="text-xl font-semibold mb-4 mt-6 text-left">Medical Condition</h2>
                <Link href={`/MedicalCondition/CreateMedicalConditionPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Add Medical Condition
                </Link>
                <Link href={`/MedicalCondition/ListMedicalConditionPage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    List Medical Condition
                </Link>
                <h2 className="text-xl font-semibold mb-4 mt-6 text-left">Operation Rooms</h2>
                <Link href={`/OperationRoom/AssignOperationRoomPage`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Assign Operation Room
                </Link>
                <Link href={`/OperationRoom/CreateTypeRoomPage`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Create Type of Room
                </Link>


            </nav>
        </div>
    );
};

export default Sidebar;