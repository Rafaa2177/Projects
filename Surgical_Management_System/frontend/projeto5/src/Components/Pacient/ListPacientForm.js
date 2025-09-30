"use client";
import React, { useState } from 'react';
import '../../app/globals.css';

const ListPacientForm = ({ onSearch }) => {
    const [filterBy, setFilterBy] = useState('');
    const [licenseNumber, setLicenseNumber] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [specialization, setSpecialization] = useState('');
    
    const handleFilterByChange = (e) => {
        setFilterBy(e.target.value);
        setEmail('');
        
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (typeof onSearch === 'function') {
            onSearch({ filterBy,  email });
        }
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="flex flex-col items-start gap-6 p-6 bg-white rounded-lg shadow-md max-w-lg w-80  h-15"
        >
            <h2 className="text-lg font-bold text-gray-700">List Patient</h2>

            {/* Selecionar um atributo */}
            <select
                value={filterBy}
                onChange={handleFilterByChange}
                className="w-full px-4 py-2 text-lg border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
                <option value="">Select an option</option>
                <option value="email">Email</option>
            </select>

            {/* Campos de pesquisa  */}
            {filterBy === 'email' && (
                <input
                    type="text"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder={`Search by ${filterBy}`}
                    className="w-full px-4 py-2 text-lg border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
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

export default ListPacientForm;
