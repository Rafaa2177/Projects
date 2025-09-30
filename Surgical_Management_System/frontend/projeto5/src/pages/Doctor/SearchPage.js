"use client";

import React, { useState } from "react";
import SideBarStaff from "../../Components/Others/SideBarStaff";
/**
 * React component for the Search Page.
 * This page allows doctors to search for patient records by allergies or medical conditions.
 */
const SearchPage = () => {
    /**
     * State to manage the type of search (allergies or medical conditions).
     */
    const [searchType, setSearchType] = useState("allergies");

    /**
     * State to manage the search input value.
     */
    const [searchInput, setSearchInput] = useState("");

    /**
     * State to manage the search results.
     */
    const [results, setResults] = useState(null);

    /**
     * State to manage the loading status.
     */
    const [isLoading, setIsLoading] = useState(false);

    /**
     * Profile image URL.
     */
    const profileImage = "/4792929.png";

    /**
     * Full name of the doctor.
     */
    const fullName = "Doctor";

    /**
     * Fetches patient information based on a list of emails.
     * @param {Array} emails - The list of patient emails.
     */
    const fetchPatientsInformation = async (emails) => {
        try {
            const patientPromises = emails.map(email =>
                fetch(`https://localhost:5001/api/Patients/email/${email}`)
                    .then(res => res.json())
            );

            const patientDetails = await Promise.all(patientPromises);
            setResults(patientDetails);
        } catch (error) {
            console.error("Error fetching patient details:", error);
            setResults({ error: error.message });
        }
    };

    /**
     * Handles the search form submission.
     * @param {Object} e - The event object.
     */
    const handleSearch = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        setResults(null);

        try {
            const url = searchType === "allergies"
                ? `https://localhost:5001/api/Patients/patientAllergy/${searchInput}`
                : `https://localhost:5001/api/Patients/patientMedicalCondition/${searchInput}`;
            const response = await fetch(url);

            if (!response.ok) throw new Error("No patients found.");
            const data = await response.json();
            await fetchPatientsInformation(data);
        } catch (error) {
            console.error("Error fetching patients:", error);
            setResults({ error: error.message });
        } finally {
            setIsLoading(false);
        }
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
                <SideBarStaff profileImage={profileImage} fullName={fullName} />

                <div className="flex-grow p-6 flex flex-col">
                    <h1 className="text-2xl font-semibold mb-8 text-center">Search Patient Records</h1>

                    <form onSubmit={handleSearch} className="flex flex-col items-center gap-4">
                        <select
                            name="searchType"
                            value={searchType}
                            onChange={(e) => setSearchType(e.target.value)}
                            className="w-full max-w-md px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        >
                            <option value="allergies">Search by Allergies</option>
                            <option value="medicalConditions">Search by Medical Conditions</option>
                        </select>

                        <input
                            type="text"
                            value={searchInput}
                            onChange={(e) => setSearchInput(e.target.value)}
                            placeholder={`Enter ${searchType}`}
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

                    {isLoading && <p className="text-center mt-8">Loading...</p>}

                    {results && (
                        <div className="mt-8">
                            {results.error ? (
                                <p className="text-red-500 text-center">{results.error}</p>
                            ) : (
                                <div className="space-y-4">
                                    {results.map((patient) => (
                                        <div
                                            key={patient.id}
                                            className="border border-gray-300 dark:border-gray-700 rounded-lg p-4 bg-gray-50 dark:bg-gray-900"
                                        >
                                            <h3 className="text-lg font-semibold">{patient.firstName}</h3>
                                            <p>Email: {patient.email}</p>
                                            <p>Phone Number: {patient.phoneNumber}</p>
                                            <p>Allergies: {patient.allergies?.join(", ") || "None"}</p>
                                            <p>Medical Conditions: {patient.medicalConditions?.join(", ") || "None"}</p>
                                        </div>
                                    ))}
                                </div>
                            )}
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default SearchPage;