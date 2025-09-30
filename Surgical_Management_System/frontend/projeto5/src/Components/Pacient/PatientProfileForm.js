import React, { useState, useEffect } from 'react';
import '../../app/globals.css';

// Função para garantir que a data esteja no formato 'YYYY-MM-DD'
const formatDate = (dateString) => {
    if (!dateString) return "";
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
};

const PacientForm = ({ onSubmit, isDeleteForm = false, initialData = {} }) => {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        fullName: "",
        phoneNumber: "",
        email: "",
        ...initialData,
        dateOfBirth: formatDate(initialData.dateOfBirth)
    });

    useEffect(() => {
        if (isDeleteForm) {
            // Se for um formulário de apagar, preenche o form apenas com o email
            setFormData({ email: '' });
        } else if (initialData) {
            setFormData({ ...formData, ...initialData, dateOfBirth: formatDate(initialData.dateOfBirth) });
        }
    }, [isDeleteForm, initialData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    };

    return (

        <form onSubmit={handleSubmit}
              className="flex flex-col gap-4 max-w-lg mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg">
            {/* Campo Email (para apagar o pacient profile) */}
            {isDeleteForm ? (
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="Enter the email of the patient profile you want to delete"
                    className="px-6 py-4 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-lg w-full max-w-2xl mx-auto"
                    required
                />
            ) : (
                <>
                    {/* Campo First Name */}
                    <input
                        type="text"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        placeholder="First Name"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />

                    {/* Campo Last Name */}
                    <input
                        type="text"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        placeholder="Last Name"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />

                    {/* Campo Full Name */}
                    <input
                        type="text"
                        name="fullName"
                        value={formData.fullName}
                        onChange={handleChange}
                        placeholder="Full Name"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                    />

                    {/* Campo Date of Birth (Calendário) */}
                    <input
                        type="date"
                        name="dateOfBirth"
                        value={formData.dateOfBirth}
                        onChange={handleChange}
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />

                    {/* Campo Phone Number */}
                    <input
                        type="tel"
                        name="phoneNumber"
                        value={formData.phoneNumber}
                        onChange={handleChange}
                        placeholder="Phone Number"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />

                    {/* Campo Email */}
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        placeholder="Email"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />
                </>
            )}

            {/* Botão para submeter o formulário */}
            <button
                type="submit"
                className="bg-pink-400 text-white py-4 px-10 rounded-lg hover:bg-pink-300 transition-colors mt-4 text-xl w-full max-w-2xl mx-auto"
            >
                {'Update Patient Profile'}
            </button>
        </form>

    );
};

export default PacientForm;
