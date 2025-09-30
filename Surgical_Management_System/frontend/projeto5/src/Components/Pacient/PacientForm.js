import React, { useState, useEffect } from "react";
import "../../app/globals.css";

const PacientForm = ({ onSubmit, isDeleteForm = false, initialData = {} }) => {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        fullName: "",
        dateOfBirth: "",
        phoneNumber: "",
        email: "",
        allergies: [],
        medicalConditions: [],
        ...initialData,
    });

    const [allergies, setAllergies] = useState([]);
    const [medicalConditions, setMedicalConditions] = useState([]);

    // Fetch allergies
    const fetchAllergies = async () => {
        try {
            const response = await fetch("http://localhost:4000/api/allergies");
            if (!response.ok) throw new Error("Failed to fetch allergies");
            const data = await response.json();
            setAllergies(data);
        } catch (error) {
            console.error("Error fetching allergies:", error);
        }
    };

    // Fetch medical conditions
    const fetchConditions = async () => {
        try {
            const response = await fetch("http://localhost:4000/api/medicalCondition");
            if (!response.ok) throw new Error("Failed to fetch medical conditions");
            const data = await response.json();
            setMedicalConditions(data);
        } catch (error) {
            console.error("Error fetching medical conditions:", error);
        }
    };

    useEffect(() => {
        fetchAllergies();
        fetchConditions();
    }, []);

    useEffect(() => {
        if (isDeleteForm) {
            setFormData({ email: "" });
        } else if (initialData?.dateOfBirth) {
            const formattedDateOfBirth = new Date(initialData.dateOfBirth).toISOString().split("T")[0];
            setFormData({
                ...formData,
                ...initialData,
                dateOfBirth: formattedDateOfBirth,
            });
        } else {
            setFormData({
                ...formData,
                ...initialData,
            });
        }
    }, [isDeleteForm, initialData]);

    const handleCheckboxChange = (e, field, items) => {
        const { value, checked } = e.target;
        const item = items.find((i) => i.name === value);

        setFormData((prevData) => ({
            ...prevData,
            [field]: checked
                ? [...prevData[field], item] // Adiciona o objeto completo
                : prevData[field].filter((i) => i.id !== item.id), // Remove o objeto pelo id
        }));
    };


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const payload = {
            ...formData,
            allergies: formData.allergies.map((a) => a.name), // Enviar apenas IDs
            medicalConditions: formData.medicalConditions.map((mc) => mc.name), // Enviar apenas IDs
        };

        onSubmit(payload);
    };



    return (
        <form
            onSubmit={handleSubmit}
            className="flex flex-col gap-4 max-w-lg mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg"
        >
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
                    <input
                        type="text"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        placeholder="First Name"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />
                    <input
                        type="text"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        placeholder="Last Name"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />
                    <input
                        type="text"
                        name="fullName"
                        value={formData.fullName}
                        onChange={handleChange}
                        placeholder="Full Name"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />

                    <input
                        type="date"
                        name="dateOfBirth"
                        value={formData.dateOfBirth}
                        onChange={handleChange}
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />
                    <input
                        type="tel"
                        name="phoneNumber"
                        value={formData.phoneNumber}
                        onChange={handleChange}
                        placeholder="Phone Number"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        placeholder="Email"
                        className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
                        required
                    />

                    <fieldset>
                        <legend className="text-lg font-semibold mb-2">Allergies</legend>
                        {allergies.map((allergy) => (
                            <label key={allergy.id} className="block">
                                <input
                                    type="checkbox"
                                    value={allergy.name}
                                    checked={formData.allergies.some((a) => a.id === allergy.id)}
                                    onChange={(e) => handleCheckboxChange(e, "allergies", allergies)}
                                    className="mr-2"
                                />
                                {allergy.name}
                            </label>
                        ))}
                    </fieldset>

                    <fieldset>
                        <legend className="text-lg font-semibold mb-2">Medical Conditions</legend>
                        {medicalConditions.map((condition) => (
                            <label key={condition.id} className="block">
                                <input
                                    type="checkbox"
                                    value={condition.name}
                                    checked={formData.medicalConditions.some((mc) => mc.id === condition.id)}
                                    onChange={(e) => handleCheckboxChange(e, "medicalConditions", medicalConditions)}
                                    className="mr-2"
                                />
                                {condition.name}
                            </label>
                        ))}
                    </fieldset>
                </>
            )}
            <button
                type="submit"
                className="bg-pink-400 text-white py-4 px-10 rounded-lg hover:bg-pink-300 transition-colors mt-4 text-xl w-full max-w-2xl mx-auto"
            >
                {isDeleteForm ? "Delete Patient Profile" : "Create Patient Profile"}
            </button>
        </form>
    );
};

export default PacientForm;
