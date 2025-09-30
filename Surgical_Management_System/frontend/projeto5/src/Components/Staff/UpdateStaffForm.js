import React, { useState, useEffect } from 'react';
import '../../app/globals.css';

const UpdateStaffForm = ({ onSubmit, initialData = {} }) => {
    const [formData, setFormData] = useState({
        email: "",
        specialization: "",
        phoneNumber: "",
        timeSlots: [],
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
    
    // UseEffect para definir o estado inicial com base no tipo de formulário
   useEffect(() => {
       if (initialData) {
           setFormData({
               email: initialData.email || "",
               specialization: initialData.specialization || "",
               phoneNumber: initialData.phoneNumber || "",
               timeSlots: initialData.timeSlots || [], // Garante que timeSlots seja um array
           });
       }
   }, [initialData]);
               
                   
    // Função de manipulação de mudança de input
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevData => ({ ...prevData, [name]: value }));
    };

    // Função de envio do formulário
    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
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
    
    return (
        <form
            onSubmit={handleSubmit}
            className="flex flex-col gap-4 max-w-lg mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg"
        >
            <>
                {/* Campos adicionais para criar um novo perfil de paciente */}
                <input
                    type="tel"
                    name="phoneNumber"
                    value={formData.phoneNumber}
                    onChange={handleChange}
                    placeholder="Phone Number"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                    
                />
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="Email"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                    
                />
                <select
                    name="specialization"
                    value={formData.specialization}
                    onChange={(e) => setFormData({...formData, specialization: e.target.value})}
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                >
                    <option value="" disabled>Select a specialization</option>
                    {Array.isArray(specialization) && specialization.map((spec) => (
                        <option key={spec.id} value={spec.name}>
                            {spec.name}
                        </option>
                    ))}
                </select>
            </>
            <div className="flex flex-col gap-2">
                <label className="text-gray-700">Time Slots:</label>
                {formData.timeSlots.map((slot, index) => (
                    <div key={index} className="flex gap-2 items-center">
                        <input
                            type="date"
                            value={slot.startDate}
                            onChange={(e) => handleTimeSlotChange(index, "startDate", e.target.value)}
                            className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        <input
                            type="time"
                            value={slot.startTime}
                            onChange={(e) => handleTimeSlotChange(index, "startTime", e.target.value)}
                            className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        <input
                            type="date"
                            value={slot.endDate}
                            onChange={(e) => handleTimeSlotChange(index, "endDate", e.target.value)}
                            className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        <input
                            type="time"
                            value={slot.endTime}
                            onChange={(e) => handleTimeSlotChange(index, "endTime", e.target.value)}
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

            <button
                type="submit"
                className="bg-pink-400 text-white py-4 px-10 rounded-lg hover:bg-pink-300 transition-colors mt-4 text-xl w-full max-w-2xl mx-auto"
            >
                Update Staff Profile
            </button>
        </form>

);
            };


            export default UpdateStaffForm;
