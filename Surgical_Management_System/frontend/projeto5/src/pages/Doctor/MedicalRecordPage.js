"use client";

import React, { useEffect, useState } from "react";
import SideBarStaff from "../../Components/Others/SideBarStaff";
import { jsPDF } from "jspdf";
import DownloadMedicalHistory from "./DownloadMedicalPage";

const MedicalRecordPage = () => {
    const [email, setEmail] = useState("");
    const [medicalData, setMedicalData] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [allergiesData, setAllergiesData] = useState([]);
    const [medicalConditionsData, setMedicalConditionsData] = useState([]);
    const [isLoadingAllergies, setIsLoadingAllergies] = useState(false);
    const [isLoadingConditions, setIsLoadingConditions] = useState(false);
    const [isUpdating, setIsUpdating] = useState(false);
    const [allergies, setAllergies] = useState([]);
    const [medicalConditions, setMedicalConditions] = useState([]);
    const [patientData, setPatientData] = useState(null);

    const [formData, setFormData] = useState({
        email: "",
        allergies: [],
        medicalConditions: [],
    });

    const profileImage = "/4792929.png";
    const fullName = email ? email.split("@")[0] : "Doctor";

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSearch = (e) => {
        e.preventDefault();
        if (formData.email.trim()) {
            setEmail(formData.email.trim());
            fetchPatients(formData.email.trim());
        }
    };

    const fetchPatients = async (emailToFetch) => {
        setIsLoadingAllergies(true);
        setIsLoadingConditions(true);

        try {
            // Fetch allergies
            const allergiesResponse = await fetch(
                `https://localhost:5001/api/Patients/allergy/${emailToFetch}`
            );
            if (!allergiesResponse.ok) throw new Error("Allergies not found.");
            const allergiesData = await allergiesResponse.json();
            setAllergiesData(allergiesData);

            const patientResponse = await fetch(`https://localhost:5001/api/Patients/email/${emailToFetch}`
            );
            if (!patientResponse.ok) throw new Error("Patient not found.");
            const patientData = await patientResponse.json();
            setPatientData(patientData);

            // Fetch medical conditions
            const conditionsResponse = await fetch(
                `https://localhost:5001/api/Patients/medicalCondition/${emailToFetch}`
            );
            if (!conditionsResponse.ok) throw new Error("Medical conditions not found.");
            const conditionsData = await conditionsResponse.json();
            setMedicalConditionsData(conditionsData);
        } catch (error) {
            console.error("Error fetching patient data:", error);
            setAllergiesData([]);
            setMedicalConditionsData([]);
        } finally {
            setIsLoadingAllergies(false);
            setIsLoadingConditions(false);
        }
    };



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

    const fetchMedicalConditions = async () => {
        try {
            const response = await fetch("http://localhost:4000/api/medicalCondition");
            if (!response.ok) throw new Error("Failed to fetch medical conditions");
            const data = await response.json();
            setMedicalConditions(data);
        } catch (error) {
            console.error("Error fetching medical conditions:", error);
        }
    };

    const handleCheckboxChange = (e, field, items) => {
        const { value, checked } = e.target;
        const item = items.find((i) => i.name === value);

        setFormData((prevData) => ({
            ...prevData,
            [field]: checked
                ? [...(prevData[field] || []), item]
                : (prevData[field] || []).filter((i) => i.name !== item.name),
        }));
    };

    const handleUpdateData = async (field, apiPath) => {
        try {
            for (const item of formData[field]) {
                const response = await fetch(
                    `https://localhost:5001/api/Patients/${apiPath}/${email}/${item.name}`,
                    {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json",
                        },
                    }
                );

                if (!response.ok) {
                    const errorMessage = await response.text();
                    throw new Error(`Failed to update ${field} ${item.name}: ${errorMessage}`);
                }
            }

            if (field === "allergies") setAllergiesData(formData.allergies.map((a) => a.name));
            if (field === "medicalConditions") setMedicalConditionsData(formData.medicalConditions.map((c) => c.name));
            setIsUpdating(false);
            alert(`${field.charAt(0).toUpperCase() + field.slice(1)} updated successfully!`);
        } catch (error) {
            console.error(`Error updating ${field}:`, error);
            alert(error.message);
        }
    };

    const handleOpenUpdateModal = async () => {
        try {
            await fetchAllergies();
            await fetchMedicalConditions();

            setFormData((prevData) => ({
                ...prevData,
                allergies: allergiesData.map((name) => ({
                    id: allergies.find((a) => a.name === name)?.id || Math.random(),
                    name,
                })),
                medicalConditions: medicalConditionsData.map((name) => ({
                    id: medicalConditions.find((c) => c.name === name)?.id || Math.random(),
                    name,
                })),
            }));

            setIsUpdating(true);
        } catch (error) {
            console.error("Error opening update modal:", error);
        }
    };

    const renderList = (items, message) => {
        if (!items || items.length === 0) {
            return <p className="text-center text-gray-500">{message}</p>;
        }

        return (
            <ul className="list-disc ml-8">
                {items.map((item, index) => (
                    <li key={index} className="mb-2">
                        {item}
                    </li>
                ))}
            </ul>
        );
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
                <SideBarStaff profileImage={profileImage} fullName={fullName} email={email} />

                <div className="flex-grow p-6 flex flex-col">
                    <div className="flex flex-col mb-8">
                        <h1 className="text-2xl font-semibold mb-4 text-center">Patient Records</h1>

                        <form onSubmit={handleSearch} className="flex flex-col items-center gap-4 mt-20">
                            <input
                                type="text"
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                placeholder="Enter patient's email"
                                className="w-full max-w-md px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                                required
                            />
                            <button
                                type="submit"
                                className="bg-pink-400 text-white py-2 px-6 rounded-lg hover:bg-pink-300 transition-colors"
                            >
                                Search
                            </button>
                        </form>
                    </div>

                    {isLoadingAllergies && <p className="text-center">Loading...</p>}

                    <div className="mt-8">
                        <h2 className="text-xl font-semibold mb-4">Allergies</h2>
                        {renderList(allergiesData, "No allergies found for this patient.")}

                        <h2 className="text-xl font-semibold mt-8 mb-4">Medical Conditions</h2>
                        {renderList(medicalConditionsData, "No medical conditions found for this patient.")}

                        <button
                            onClick={handleOpenUpdateModal}
                            className="bg-pink-400 text-white py-2 px-6 mt-4 rounded-lg hover:bg-pink-600 transition-colors"
                        >
                            Update Records
                        </button>
                    </div>

                    {patientData && allergiesData && medicalConditionsData && (
                        <DownloadMedicalHistory
                            email={email}
                            medicalData={medicalData}
                            patientData={patientData}
                            allergiesData={allergiesData}
                            medicalConditionsData={medicalConditionsData}
                        />
                    )}

                    {isUpdating && (
                        <div className="mt-4">
                            <fieldset>
                                <legend className="text-lg font-semibold mb-2">Allergies</legend>
                                {allergies.map((allergy) => (
                                    <label key={allergy.id} className="block">
                                        <input
                                            type="checkbox"
                                            value={allergy.name}
                                            checked={formData.allergies.some((a) => a.name === allergy.name)}
                                            onChange={(e) => handleCheckboxChange(e, "allergies", allergies)}
                                            className="mr-2"
                                        />
                                        {allergy.name}
                                    </label>
                                ))}
                            </fieldset>

                            <fieldset className="mt-4">
                                <legend className="text-lg font-semibold mb-2">Medical Conditions</legend>
                                {medicalConditions.map((condition) => (
                                    <label key={condition.id} className="block">
                                        <input
                                            type="checkbox"
                                            value={condition.name}
                                            checked={formData.medicalConditions.some((c) => c.name === condition.name)}
                                            onChange={(e) => handleCheckboxChange(e, "medicalConditions", medicalConditions)}
                                            className="mr-2"
                                        />
                                        {condition.name}
                                    </label>
                                ))}
                            </fieldset>

                            <button
                                onClick={() => handleUpdateData("allergies", "allergy")}
                                className="bg-pink-400 text-white py-2 px-6 mt-4 rounded-lg hover:bg-pink-600 transition-colors"
                            >
                                Update Allergies
                            </button>
                            <button
                                onClick={() => handleUpdateData("medicalConditions", "condition")}
                                className="bg-blue-400 text-white py-2 px-6 ml-2 mt-4 rounded-lg hover:bg-blue-600 transition-colors"
                            >
                                Update Conditions
                            </button>
                            <button
                                onClick={() => setIsUpdating(false)}
                                className="bg-gray-500 text-white py-2 px-6 ml-2 mt-4 rounded-lg hover:bg-gray-400 transition-colors"
                            >
                                Cancel
                            </button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default MedicalRecordPage;
