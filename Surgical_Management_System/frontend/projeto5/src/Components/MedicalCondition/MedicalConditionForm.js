"use client"
import React, { useState, useEffect } from 'react';
import '../../app/globals.css';

const AllergyForm = ({ onSubmit}) => {
    const [formData, setFormData] = useState({
        name: "",
        code: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    };


    return (
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-full max-w-lg mx-auto p-4">
            <>
                <input
                    type="text"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    placeholder="Name"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                />
                <input
                    type="text"
                    name="code"
                    value={formData.code}
                    onChange={handleChange}
                    placeholder="Code"
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                />
               
            </>


            <button
                type="submit"
                className="bg-pink-400 text-white py-2 px-6 rounded-lg hover:bg-pink-300 transition-colors mt-4"
            >
                Add Medical Condition
            </button>
        </form>
    );
};

export default AllergyForm;
