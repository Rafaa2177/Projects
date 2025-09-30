import React, { useState } from 'react';

const Algorithm = () => {
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchSolution = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await fetch('http://localhost:4000/api/solution', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    room: 'or1',
                    day: 20241028,
                    heuristic: 'greedy',
                }),
            });

            if (!response.ok) {
                throw new Error('Failed to fetch solution');
            }

            const data = await response.json();
            setResult(data);
        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-4">
            <h1 className="text-3xl font-bold mb-6">Solution Page</h1>
            <button
                onClick={fetchSolution}
                className="bg-pink-500 text-white py-2 px-4 rounded mb-6"
            >
                Fetch Solution
            </button>
            {loading && <p>Loading...</p>}
            {error && <p className="text-red-500">{error}</p>}
            {result && (
                <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-4xl">
                    <h2 className="text-2xl font-bold mb-4">Execution Time: {result.executionTime} seconds</h2>
                    <h3 className="text-xl font-semibold mb-2">Schedule:</h3>
                    <div className="overflow-x-auto">
                        <table className="min-w-full bg-white">
                            <thead>
                                <tr>
                                    <th className="py-2 px-4 border-b">Start Time</th>
                                    <th className="py-2 px-4 border-b">End Time</th>
                                    <th className="py-2 px-4 border-b">Operation</th>
                                </tr>
                            </thead>
                            <tbody>
                                {result.schedule[0].finalAgenda.map((item, index) => (
                                    <tr key={index}>
                                        <td className="py-2 px-4 border-b">{item[0]}</td>
                                        <td className="py-2 px-4 border-b">{item[1]}</td>
                                        <td className="py-2 px-4 border-b">{item[2]}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Algorithm;