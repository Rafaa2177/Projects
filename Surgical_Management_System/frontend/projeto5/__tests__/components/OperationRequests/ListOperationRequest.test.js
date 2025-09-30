import React from 'react';
import '@testing-library/jest-dom';
import { render, screen } from '@testing-library/react';
import ListOperationRequestPage from '../../../src/pages/Staff/ListOperationRequestPage';

jest.mock('next/router', () => ({
    useRouter: jest.fn(),
}));
describe('ListOperationRequestPage - Button Existence', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        global.fetch = jest.fn().mockResolvedValue({
            ok: true,
            json: jest.fn().mockResolvedValue([
                {
                    id: '1',
                    doctorId: 'D001',
                    patientId: 'P001',
                    operationType: 'Surgery',
                    operationDate: '2024-11-22',
                    priority: 'High',
                },
            ]),
        });
    });

    it('should render "Apagar" button', async () => {
        render(<ListOperationRequestPage />);

        // Wait for the fetch to complete and content to render
        await screen.findByText('Surgery');

        // Verify the "Apagar" button exists
        const deleteButton = screen.getByRole('button', { name: 'Apagar' });
        expect(deleteButton).toBeInTheDocument();
    });

    it('should render "Editar" button', async () => {
        render(<ListOperationRequestPage />);

        // Wait for the fetch to complete and content to render
        await screen.findByText('Surgery');

        // Verify the "Editar" button exists
        const editButton = screen.getByRole('button', { name: 'Editar' });
        expect(editButton).toBeInTheDocument();
    });
});
