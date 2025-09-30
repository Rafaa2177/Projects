"use client"
import React, { useState, useEffect } from 'react';
import '../../app/globals.css';
import StaffDto from '../../dtos/StaffDto';

const StaffForm = ({ onSubmit, initialData}) => {
    const [formData, setFormData] = useState({
        licenseNumber: "", 
        firstName: "",
        lastName: "",
        specialization: "",
        email: "", 
        phoneNumber: "",
        timeSlots: [],
        role: "",
        ...initialData,
    });
    const [specialization, setSpecialization] = useState([]);

    // Fetch specializations
    const fetchSpecializations = async () => {
        try {
            const response = await fetch("http://localhost:4000/api/specialization");
            if (!response.ok) throw new Error("Failed to fetch specializations");
            const data = await response.json();
            setSpecialization(data);
        } catch (error) {
            console.error("Error fetching specializations:", error);
        }
    };

    useEffect(() => {
        fetchSpecializations();
    }, []);
    


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleTimeSlotChange = (index, field, value) => {
        const newTimeSlots = [...formData.timeSlots];
        newTimeSlots[index] = {
            ...newTimeSlots[index],
            [field]: value
        };
        setFormData({ ...formData, timeSlots: newTimeSlots });
    };

    const addTimeSlot = () => {
        setFormData({
            ...formData,
            timeSlots: [...formData.timeSlots, { startDate: "", startTime: "", endDate: "", endTime: "" }]
        });
    };

    const removeTimeSlot = (index) => {
        const newTimeSlots = formData.timeSlots.filter((_, i) => i !== index);
        setFormData({ ...formData, timeSlots: newTimeSlots });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const processedTimeSlots = formData.timeSlots.map(slot => {
            const startTime = new Date(`${slot.startDate}T${slot.startTime}`);
            const endTime = new Date(`${slot.endDate}T${slot.endTime}`);

            if (endTime <= startTime) {
                alert("End time must be after start time for each time slot.");
                throw new Error("End time must be after start time.");
            }

            return { startTime, endTime };
        });

        const staffData = {
            ...StaffDto,
            firstName: formData.firstName,
            lastName: formData.lastName,
            email: formData.email,
            phoneNumber: formData.phoneNumber,
            licenseNumber: formData.licenseNumber,
            role: formData.role,
            specialization: formData.specialization[0]?.name || "", // Send as a single string value
            timeSlots: processedTimeSlots,
            active: formData.active
        };

        onSubmit(staffData);
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-full max-w-lg mx-auto p-4">
            <>
                <input
                    type="text"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    placeholder="First Name"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
                <input
                    type="text"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                    placeholder="Last Name"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />

                <select
                    name="specialization"
                    value={formData.specialization}
                    onChange={(e) => setFormData({...formData, specialization: e.target.value})}
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                >
                    <option value="" disabled>Select a specialization</option>
                    {specialization.map((spec) => (
                        <option key={spec.id} value={spec.name}>
                            {spec.name}
                        </option>
                    ))}
                </select>

                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="Email"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
                <input
                    type="tel"
                    name="phoneNumber"
                    value={formData.phoneNumber}
                    onChange={handleChange}
                    placeholder="Phone Number"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />

                <div className="flex flex-col gap-2">
                    <label className="text-gray-700">Time Slots:</label>
                    {formData.timeSlots.map((slot, index) => (
                        <div key={index} className="flex gap-2 items-center">
                            <input
                                type="date"
                                value={slot.startDate}
                                onChange={(e) => handleTimeSlotChange(index, 'startDate', e.target.value)}
                                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            <input
                                type="time"
                                value={slot.startTime}
                                onChange={(e) => handleTimeSlotChange(index, 'startTime', e.target.value)}
                                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            <input
                                type="date"
                                value={slot.endDate}
                                onChange={(e) => handleTimeSlotChange(index, 'endDate', e.target.value)}
                                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            <input
                                type="time"
                                value={slot.endTime}
                                onChange={(e) => handleTimeSlotChange(index, 'endTime', e.target.value)}
                                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            <button
                                type="button"
                                onClick={() => removeTimeSlot(index)}
                                className="text-red-500 hover:text-red-700 focus:outline-none"
                            >
                                ✕
                            </button>
                        </div>
                    ))}
                    <button
                        type="button"
                        onClick={addTimeSlot}
                        className="bg-pink-400 text-white py-1 px-4 rounded-lg hover:bg-pink-300 transition-colors mt-2"
                    >
                        Add Time Slot
                    </button>
                </div>

                <select
                    name="role"
                    id="role"
                    value={formData.role}
                    onChange={handleChange}
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                >
                    <option value="" disabled>Role</option>
                    <option value="nurse">Nurse</option>
                    <option value="doctor">Doctor</option>
                </select>
            </>


            <button
                type="submit"
                className="bg-pink-400 text-white py-2 px-6 rounded-lg hover:bg-pink-300 transition-colors mt-4"
            >
                Add Staff
            </button>
        </form>
    );
};

export default StaffForm;
