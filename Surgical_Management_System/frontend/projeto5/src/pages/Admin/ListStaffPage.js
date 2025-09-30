import React, { useState } from 'react';
import Sidebar from "../../Components/Others/SideBar";
import ListStaffForm from "../../Components/Staff/ListStaffForm";
import { useRouter } from 'next/router';
import DoctorDto from "../../dtos/DoctorDto";
import StaffDto from "../../dtos/StaffDto";


const ListStaffPage = () => {
    const [staff, setStaffs] = useState([]);
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

    const roleMap = {
        0: "Doctor",
        1: "Nurse"
    };
  

    const handleSearch = async (filters = {}) => {
        setLoading(true);
        setError(null);

        try {
            let response;
            const { filterBy, licenseNumber, firstName, lastName, email, specialization } = filters;

            if (filterBy === 'email' && email) {
                response = await fetch(`https://localhost:5001/api/Staffs/email/${email}`);
            } else if (filterBy === 'licenseNumber' && licenseNumber) {
                response = await fetch(`https://localhost:5001/api/Staffs/${licenseNumber}`);
            } else if (filterBy === 'name' && firstName && lastName) {
                response = await fetch(`https://localhost:5001/api/Staffs/name/${firstName}/${lastName}`);
            } else if (filterBy === 'specialization' && specialization) {
                response = await fetch(`http://localhost:4000/api/specialization`);
            } else {
                response = await fetch("https://localhost:5001/api/Staffs/active");
            }

            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }

            const data = await response.json();
            const staffDto = data.map(staff => ({
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
                active: staff.active
            }));
            setStaffs(staffDto);
        } catch (err) {
            console.error("Error during fetch:", err);
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    const deleteStaff = async (licenseNumber) => {
        try {
            const confirmation = window.confirm(`Are you sure you want to delete the staff member?`);
            if (!confirmation) return;

            const response = await fetch(`https://localhost:5001/api/staffs/${licenseNumber}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(await response.text());
            }

            setStaffs(prevStaffs => prevStaffs.filter(staffMember => staffMember.licenseNumber?.value !== licenseNumber));
            
            setIsSuccess(true);
            setTimeout(() => setIsSuccess(false), 7000);
        } catch (error) {
            console.error(`Error deleting staff: ${error.message}`);
        }
    };

    const handleEditStaff = (staffMember) => {
        if (staffMember) {
            router.push(`/Admin/UpdateStaffPage?email=${staffMember.email}`);
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
                    <h1 className="text-3xl font-semibold text-center mt-2">List Staffs</h1>

                    <div className="w-full max-w-3xl bg-white p-6 rounded-lg shadow-md overflow-y-auto"
                         style={{ maxHeight: '400px' }}>
                        {loading ? (
                            <p className="text-gray-500">Loading...</p>
                        ) : staff.length > 0 ? (
                            staff.map((staffMember, index) => (
                                <div key={index} className="mb-4 p-4 border-b border-gray-300">
                                    <h2 className="text-lg font-bold text-gray-700 mb-2">Staff {index + 1}</h2>
                                    <p><strong>Name:</strong> {staffMember.fullName}</p>
                                    <p><strong>License Number:</strong> {staffMember.licenseNumber?.value || "No license number"}</p>
                                    <p><strong>Specialization:</strong> {staffMember.specialization}</p>
                                    <p><strong>Phone Number:</strong> {staffMember.phoneNumber}</p>
                                    <p><strong>Email:</strong> {staffMember.email}</p>
                                    <p><strong>Role:</strong> {roleMap[staffMember.role]}</p>
                                    <p><strong>Active:</strong> {staffMember.active ? "Yes" : "No"}</p>

                                    <div className="flex gap-4 mt-4">
                                        <button
                                            onClick={() => handleEditStaff(staffMember)}
                                            className="bg-pink-400 text-white py-2 px-4 rounded hover:bg-pink-300"
                                        >
                                            Edit
                                        </button>
                                        <button
                                            onClick={() => deleteStaff(staffMember.licenseNumber?.value)}
                                            className="bg-pink-700 text-white py-2 px-4 rounded hover:bg-pink-600"
                                        >
                                            Delete
                                        </button>
                                    </div>
                                </div>
                            ))
                        ) : (
                            <p className="text-gray-500">No staff found.</p>
                        )}
                    </div>

                    <div className="absolute bottom-10 right-6">
                        <button
                            onClick={() => handleSearch()}
                            className="w-28 bg-pink-400 text-white font-semibold py-2 px-4 rounded hover:bg-pink-300"
                        >
                            List All Staffs
                        </button>
                    </div>
                    
                    {isSuccess && (
                        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg text-center">
                            Staff was deleted successfully.
                        </div>
                    )}
                    <div className="w-full max-w-sm mb-4">
                        <ListStaffForm onSearch={handleSearch}/>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ListStaffPage;
