import React, { useState, useEffect } from 'react';

const BackOfficeForm = ({ onSubmit, userId }) => {
    const [formData, setFormData] = useState({
        email: "",
        role: "",
        
    });

    useEffect(() => {
        if (userId) {
            // Lógica para buscar os dados do paciente usando licenseNumber
        }
    }, [userId]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        
        // Envia o formData processado para a função onSubmit
        onSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-full max-w-lg mx-auto p-4">
            <input
                type="text"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Email"
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <select
                name="role"
                value={formData.role}
                onChange={handleChange}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
                <option value="" disabled>Role</option>
                <option value="admin">Admin</option>
                <option value="doctor">Doctor</option>
                <option value="nurse">Nurse</option>
                <option value="patient">Patient</option>
                <option value="technician">Technician</option>
            </select>
            
            <button
                type="submit"
                className="bg-pink-400 text-white py-2 px-6 rounded-lg hover:bg-pink-300 transition-colors mt-4"
            >
                Create User
            </button>
        </form>
    );
};

export default BackOfficeForm;
