import React from 'react';
import '@testing-library/jest-dom';
import { render, screen, fireEvent, waitFor } from '@testing-library/react'; // Mova isso para o topo
import OperationForm from '../../../src/Components/OperationRequests/OperationForm'; // Ajuste o caminho conforme necessário

// Mock fetch API
global.fetch = jest.fn();

describe('OperationForm', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: jest.fn().mockResolvedValue([
                {
                    id: '1',
                    recordNumber: { value: 'REC001' },
                },
            ]),
        });
        global.fetch.mockResolvedValueOnce({
            ok: true,
            json: jest.fn().mockResolvedValue([
                {
                    id: '2',
                    licenseNumber: { value: 'DOC001' },
                    role: 0,
                },
            ]),
        });
    });

        it('fetches and displays patients and doctors', async () => {
            render(<OperationForm onSubmit={jest.fn()} />);

           
            expect(global.fetch).toHaveBeenCalledWith('https://localhost:5001/api/Patients');
            expect(global.fetch).toHaveBeenCalledWith('https://localhost:5001/api/Staffs/active');

           
            await waitFor(() => {
                expect(screen.getByText('REC001')).toBeInTheDocument();
                expect(screen.getByText('DOC001')).toBeInTheDocument();
            });
        });
        

        it('handles API errors gracefully', async () => {
            global.fetch.mockRejectedValueOnce(new Error('API error'));
            render(<OperationForm onSubmit={jest.fn()} />);

           
            await waitFor(() => {
                expect(global.fetch).toHaveBeenCalledWith('https://localhost:5001/api/Patients');
                expect(global.fetch).toHaveBeenCalledWith('https://localhost:5001/api/Staffs/active');
            });
        });
        it('submits the operation request when the submit button is clicked', async () => {
            const onSubmit = jest.fn(); 
            render(<OperationForm onSubmit={onSubmit} />);
            
            const submitButton = screen.getByRole('button', { name: /Submit Operation Request/i });
            fireEvent.click(submitButton);
            
        });
});
