import React, {useEffect, useState} from 'react';
import Link from "next/link";

interface SidebarProps {
    profileImage: string;
    fullName: string;
    email: string;
}

const SidebarPatient: React.FC<SidebarProps> = ({ profileImage}) => {


    const [fullName, setFullName] = useState("");
    const getInitialEmail = () => {
        if (typeof window !== "undefined") {
            return localStorage.getItem('email') || ""; // Return the stored email or an empty string
        }
        return ""; // Fallback for SSR
    };

    const email = getInitialEmail();

    const getUserData = async () => {
        try {
            const response = await fetch(`https://localhost:5001/api/Patients/email/${email}`, { method: "GET" });
            if (!response.ok) {
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
                    localStorage.setItem('docId', userData.recordNumber.value);
                }
            })();
        }
    }, [email]);




    return (
        <div className="w-64 bg-white dark:bg-gray-800 border-r border-gray-300 dark:border-gray-700 flex flex-col items-start p-4">
            <Link href={`/Patient/page?email=${email}`}>
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

            
                <h2 className="text-xl font-semibold mb-4 mt-6 text-left">Patients</h2>
                <Link href={`/Patient/UpdateProfilePage?email=${email}`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Update Profile
                </Link>
                <Link href={`/list-staffs`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Medical Record
                </Link>
                <Link href={`/list-staffs`}
                      className="text-left text-black hover:underline cursor-pointer">
                    Appointments
                </Link>
        </div>
    );
};

export default SidebarPatient;