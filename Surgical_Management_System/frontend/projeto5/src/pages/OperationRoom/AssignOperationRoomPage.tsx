import React, {FormEvent, useState,useEffect} from 'react';
import Sidebar from "../../Components/Others/SideBar";
import { useRouter } from 'next/router';
import '../../app/globals.css';

interface AdminInfo {
    fullName: string;
    email: string;
    contact: string;
    profileImage: string;
}
interface RoomType{
    id: string;
    name: string;
    description: string;
}


const AssignOperationRoomPage: React.FC = () => {
    const router = useRouter();
    const [isSuccess, setIsSuccess] = useState<boolean>(false);
    const [roomTypes, setRoomTypes] = useState<RoomType[]>([]);
    const [formData, setFormData] = useState({
        room: "",
        roomType: ""
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:4000/api/operation-rooms/${formData.room}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ roomType: formData.roomType }),
            });

            if (!response.ok) {
                throw new Error(`Error: ${response.status} - ${response.statusText}`);
            }

            const result = await response.json();
            console.log('RoomType updated:', result);
            setIsSuccess(true);
        } catch (error) {
            console.error('Error submitting:', error);
        }
    };
    useEffect(() =>
    {
        const fetchRoomTypes = async () => {
            try {
                const response = await fetch('http://localhost:4000/api/roomTypes');
                const data = await response.json();
                const roomTypesDTO = data.map((roomType: any) => ({
                    id: roomType.id,
                    name: roomType.name,
                    description: roomType.description
                }));
                setRoomTypes(roomTypesDTO);
            } catch (error) {
                console.error('Error fetching room types:', error);
            }
        };
        fetchRoomTypes();

    },[]);
    const adminInfo: AdminInfo = {
        fullName: "Admin Name",
        email: "admin@example.com",
        contact: "----",
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
    <Sidebar profileImage={adminInfo.profileImage} fullName={adminInfo.fullName} email={adminInfo.email}/>

    <div className="flex-grow flex flex-col space-y-4 relative">
    <h1 className="text-3xl font-semibold text-center mt-2">Create Type of Room</h1>
    {isSuccess && (
        <div className="mb-4 p-4 bg-green-200 text-green-800 rounded-lg text-center">
            Operação realizada com sucesso!
    </div>
    )}
    <div className="w-full">

        <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-full max-w-lg mx-auto p-4">
            <label htmlFor="room" className="text-lg font-semibold">
                Room
            </label>
            <select
                name="room"
                value={formData.room}
                onChange={handleChange}
                defaultValue={"or1"}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            >
                <option value="or1">or1</option>
                <option value="or2">or2</option>
                <option value="or3">or3</option>
                <option value="or4">or4</option>
            </select>
            <label htmlFor="roomType" className="text-lg font-semibold">
                Room Type
            </label>
            <select
                name="roomType"
                value={formData.roomType}
                onChange={handleChange}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required>

                {roomTypes.map(roomType =>(
                    <option key={roomType.id} value={roomType.id}>
                        {roomType.name}
                    </option>
                ))}
            </select>
            <button
                type="submit"
                className="bg-pink-400 text-white py-2 px-6 rounded-lg hover:bg-pink-300 transition-colors mt-4"
            >
                Update Room Type
            </button>

        </form>

    </div>

    </div>

    </div>

        </div>
    );
};

export default AssignOperationRoomPage;