"use client"
import React, { useState, useEffect } from 'react';
import '../../app/globals.css';
const InactivateStaffForm = ({ onSubmit, isDeleteForm = false }) => {
    const [formData, setFormData] = useState({
        licenseNumber: "", // usado para o delete
        firstName: "",
        lastName: "",
        specialization: "",
        email: "",
        phoneNumber: "",
        timeSlots: [],
        role: ""
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };
    
    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit({ email: formData.email });
            
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-full max-w-lg mx-auto p-4 mt-36 justify-center">
                <input
                    type="text"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="Enter email to deactivate"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                />
            <button
                type="submit"
                className="bg-pink-400 text-white py-2 px-6 rounded-lg hover:bg-pink-300 transition-colors mt-4"
            >
                Deactivate Staff Profile
            </button>
        </form>
    );
};

export default InactivateStaffForm;
