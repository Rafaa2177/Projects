import React, { useState, useEffect } from 'react';
import '../../app/globals.css';

/**
 * React component for the Specialization Update Form.
 * Provides a form to update an existing specialization.
 *
 * @param {Function} onSubmit - Function to handle form submission.
 * @param {Object} initialData - Initial data to populate the form.
 */
const SpecializationUpdateForm = ({ onSubmit, initialData = {} }) => {
    const [formData, setFormData] = useState({
        name: "",
        code: "",
        description: "",
        ...initialData
    });

    useEffect(() => {
        if (initialData) {
            setFormData({ ...formData, ...initialData });
        }
    }, [initialData]);

    /**
     * Handles changes to the form inputs.
     * @param {Object} e - The event object.
     */
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    /**
     * Handles form submission.
     * @param {Object} e - The event object.
     */
    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit}
              className="flex flex-col gap-4 max-w-lg mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg">
            <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="Name"
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            />

            <input
                type="text"
                name="code"
                value={formData.code}
                onChange={handleChange}
                placeholder="Code"
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            />

            <input
                type="text"
                name="description"
                value={formData.description}
                onChange={handleChange}
                placeholder="Description"
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
            />

            <button
                type="submit"
                className="bg-pink-400 text-white py-4 px-10 rounded-lg hover:bg-pink-300 transition-colors mt-4 text-xl w-full max-w-2xl mx-auto"
            >
                Update Specialization
            </button>
        </form>
    );
};

export default SpecializationUpdateForm;