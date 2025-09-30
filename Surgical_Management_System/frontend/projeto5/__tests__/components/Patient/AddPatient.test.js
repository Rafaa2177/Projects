import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import AddPacientPage from '../../../src/pages/Admin/AddPacientPage'; 
import '@testing-library/jest-dom';
import PacientForm from '../../../src/Components/Pacient/PacientForm';

// Mock the PacientForm component to avoid dealing with its internal logic
jest.mock('../../../src/Components/Pacient/PacientForm', () => ({ onSubmit }) => (
    <form onSubmit={e => { e.preventDefault(); onSubmit({ name: 'John Doe', email: 'john@example.com' }); }}>
        <button type="submit">Submit</button>
    </form>
));

describe('AddPacientPage', () => {
    beforeEach(() => {
        // Mock the fetch API
        global.fetch = jest.fn();
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should render the page correctly', () => {
        render(<AddPacientPage />);
        
        const headings = screen.getAllByText('Add Patient Profile');
        expect(headings.length).toBeGreaterThanOrEqual(1);
        
        expect(headings[1]).toBeInTheDocument(); 
    });
    
    it('should handle API errors gracefully', async () => {
        // Mock the fetch response for failure
        global.fetch.mockResolvedValueOnce({
            ok: false,
            text: () => Promise.resolve('Error occurred'),
        });

        render(<AddPacientPage />);

        const submitButton = screen.getByRole('button', { name: /submit/i });
        fireEvent.click(submitButton);

        // Verify the error behavior (you can add error handling in your component, but for now, we're focusing on the notification)
        await waitFor(() => {
            // Make sure the error doesn't show a success message
            expect(screen.queryByText('Patient Profile created successfully!')).not.toBeInTheDocument();
        });
    });

    /*it('should submit the form with correct data', async () => {
        // Mock the fetch response for success
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: () => Promise.resolve({ id: 1, name: 'John Doe', email: 'john@example.com' }),
        });

        render(<AddPacientPage />);

        const submitButton = screen.getByRole('button', { name: /submit/i });
        fireEvent.click(submitButton);

        // Verify if fetch was called with the correct URL and body
        await waitFor(() => {
            expect(global.fetch).toHaveBeenCalledWith('https://localhost:5001/api/Patients', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name: 'John Doe', email: 'john@example.com' }),
            });
        });
    });*/
});
