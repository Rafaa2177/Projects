"use client";
import React, { useState } from 'react';
import '../../app/globals.css';

const DeleteStaffForm = ({ onSearch }) => {
    const [filterBy, setFilterBy] = useState('');
    const [licenseNumber, setLicenseNumber] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [specialization, setSpecialization] = useState('');

    const handleFilterByChange = (e) => {
        setFilterBy(e.target.value);
        setLicenseNumber('');
        setFirstName('');
        setLastName('');
        setEmail('');
        setSpecialization('');
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (typeof onSearch === 'function') {
            onSearch({ filterBy, licenseNumber, firstName, lastName, email, specialization });
        }
    };

    return (

        <form
            onSubmit={handleSubmit}
            className="flex flex-col items-start gap-6 p-6 bg-white rounded-lg shadow-md max-w-lg w-80  h-15"
        >
            <h2 className="text-lg font-bold text-gray-700">List Staff</h2>

            {/* Selecionar um atributo */}
            <select
                value={filterBy}
                onChange={handleFilterByChange}
                className="w-full px-4 py-2 text-lg border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
                <option value="">Select an option</option>
                <option value="email">Email</option>
                <option value="recordNumber">License Number</option>
                <option value="name">Name</option>
                <option value="specialization">Specialization</option>
            </select>

            {/* Campos de pesquisa  */}
            {filterBy === 'licenseNumber' && (
                <input
                    type="text"
                    value={licenseNumber}
                    onChange={(e) => setLicenseNumber(e.target.value)}
                    placeholder={`Search by ${filterBy}`}
                    className="w-full px-4 py-2 text-lg border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            )}
            {filterBy === 'email' && (
                <input
                    type="text"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder={`Search by ${filterBy}`}
                    className="w-full px-4 py-2 text-lg border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            )}

            {filterBy === 'name' && (
                <div className="flex gap-4 w-full">
                    <input
                        type="text"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        placeholder="First Name"
                        className="w-1/2 px-4 py-2 text-lg border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                    <input
                        type="text"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        placeholder="Last Name"
                        className="w-1/2 px-4 py-2 text-lg border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                </div>
            )}

            {filterBy === 'specialization' && (
                <select
                    name="specialization"
                    value={specialization}
                    onChange={(e)=> setSpecialization(e.target.value)}
                    className="px-4 py-2 border border-gray-300 dark:border-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                >
                    <option value="" disabled>Specialization</option>
                    <option value="cardiology">Cardiology</option>
                    <option value="neurology">Neurology</option>
                    <option value="pediatrics">Pediatrics</option>
                    <option value="dermatology">Dermatology</option>
                    <option value="orthopedics">Orthopedics</option>
                    <option value="general-medicine">Urology</option>
                </select>
            )}

            {/* Botão */}
            <button
                type="submit"
                className="bg-pink-400 text-white px-4 py-2 text-lg rounded-lg hover:bg-pink-300 focus:outline-none focus:ring-2 focus:ring-pink-300"
            >
                Search
            </button>
        </form>

    );
};

export default DeleteStaffForm;
