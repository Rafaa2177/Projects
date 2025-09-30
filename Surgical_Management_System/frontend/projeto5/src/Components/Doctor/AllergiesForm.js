import React, { useState, useEffect } from 'react';
import '../../app/globals.css';
import AllergiesDto from "../../dtos/AllergiesDto";

const AllergiesForm = ({ onSubmit, initialData = {} }) => {
    const [formData, setFormData] = useState({
        name: ""
    });
    const [allergies, setAllergies] = useState([]);

    useEffect(() => {
        setFormData({ ...formData, ...initialData });
    }, [initialData]);

    useEffect(() => {
        // Fetch allergies when the component is mounted
        const fetchAllergies = async () => {
            try {
                const response = await fetch('http://localhost:4000/api/allergies');
                if (!response.ok) {
                    throw new Error('Failed to fetch allergies');
                }
                const data = await response.json();
                const allergiesDto = data.map(allergy => ({
                    ...AllergiesDto,
                    id: allergy.id,
                    name: allergy.name,
                    code: allergy.code,
                    description: allergy.description
                }));
                setAllergies(allergiesDto);
            } catch (error) {
                console.error('Error fetching allergies:', error);
            }
        };

        fetchAllergies();
    }, []); // Empty dependency array to run only once on mount

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const selectedAllergy = allergies.find(allergy => allergy.name === formData.name);

        onSubmit({
            ...formData,
            allergies: selectedAllergy ? [selectedAllergy.id] : [], // Envia o ID ou um array vazio
        });
    };


    return (
        <form
            onSubmit={handleSubmit}
            className="flex flex-col gap-4 max-w-lg mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg"
        >
            <select
                name="name"
                value={formData.name}
                onChange={handleChange}
                className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                required
            >
                <option value="" disabled>Select allergy</option>
                {allergies.map(allergy => (
                    <option key={allergy.id} value={allergy.name}>
                        {allergy.name}
                    </option>
                ))}
            </select>
            <button
                type="submit"
                className="bg-pink-400 text-white py-4 px-10 rounded-lg hover:bg-pink-300 transition-colors mt-4 text-xl w-full max-w-2xl mx-auto"
            >
                {'Update Allergies'}
            </button>
        </form>
    );
};

export default AllergiesForm;
