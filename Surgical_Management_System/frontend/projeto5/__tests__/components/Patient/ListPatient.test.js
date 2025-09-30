import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import ListPacientPage from '../../../src/pages/Admin/ListPacientPage';
import { useRouter } from 'next/router';
import ListStaffPage from "../../../src/pages/Admin/ListStaffPage";

// Mock do roteador do Next.js
jest.mock('next/router', () => ({
    useRouter: jest.fn(),
}));

beforeAll(() => {
    global.confirm = jest.fn().mockReturnValue(true); // Simulate a confirmation dialog
});

describe('ListPacientPage', () => {
    const mockPush = jest.fn();

    beforeEach(() => {
        useRouter.mockReturnValue({
            query: {}, // Mock router query if needed
            pathname: '/', // Mock pathname
            push: jest.fn(), // Mock navigation functions
            replace: jest.fn(),
            asPath: '/',
            route: '/',
        });


        // Limpa os mocks entre os testes
        jest.clearAllMocks();
        global.fetch = jest.fn();
    });

    test('renders the page without errors', () => {
        render(<ListPacientPage/>);
        expect(screen.getByText('List Patients')).toBeInTheDocument();
        expect(screen.getByText('List All Patients')).toBeInTheDocument();
    });

    test('fetches and displays patients when "List All Patients" is clicked', async () => {
        const mockPatients = [
            {
                firstName: 'John', lastName: 'Doe', fullName: 'John Doe',
                recordNumber: {value: '12345'},
                phoneNumber: '555-555-5555',
                dateOfBirthFormatted: '1990-01-01',
                email: 'johndoe@example.com',
            },
        ];

        fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockPatients,
        });

        render(<ListPacientPage/>);
        fireEvent.click(screen.getByText('List All Patients'));

        await waitFor(() => expect(fetch).toHaveBeenCalledTimes(1));
        expect(fetch).toHaveBeenCalledWith('https://localhost:5001/api/Patients');

        // Use waitFor to wait for the text to appear in the DOM
        await waitFor(() => expect(screen.getByText('John Doe')).toBeInTheDocument());
        expect(screen.getByText('555-555-5555')).toBeInTheDocument();
    });

    test('deletes a patient when delete button is clicked', async () => {
        const mockPatients = [
            {
                firstName: 'John', lastName: 'Doe', fullName: 'John Doe',
                recordNumber: {value: '12345'},
                phoneNumber: '555-555-5555',
                dateOfBirthFormatted: '1990-01-01',
                email: 'johndoe@example.com',
            },
        ];

        fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockPatients,
        });

        fetch.mockResolvedValueOnce({
            ok: true,
        });

        render(<ListPacientPage/>);
        fireEvent.click(screen.getByText('List All Patients'));

        // Ensure data is loaded and displayed before proceeding
        await waitFor(() => expect(screen.getByText('John Doe')).toBeInTheDocument());

        const deleteButton = screen.getByRole('button', {name: /delete/i}); // Use role and name for better matching
        fireEvent.click(deleteButton);

        // Ensure the delete action is performed
        await waitFor(() => expect(fetch).toHaveBeenCalledTimes(2));
        expect(screen.getByText('Patient was deleted successfully.')).toBeInTheDocument();
    });
    test('navigates to edit page on "Edit" button click', async () => {
        const mockStaffData = [
            {
                id: 1,
                fullName: 'John Doe',
                licenseNumber: { value: '12345' },
                specialization: 0,
                phoneNumber: '123-456-7890',
                email: 'john@example.com',
                role: 0,
                active: true,
            },
        ];

        const pushMock = jest.fn();
        useRouter.mockImplementation(() => ({ push: pushMock }));

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockStaffData),
            })
        );

        render(<ListPacientPage />);

        const listAllButton = screen.getByRole('button', { name: 'List All Patients' });
        fireEvent.click(listAllButton);

        await waitFor(() => {
            const editButton = screen.getByRole('button', { name: 'Edit' });
            expect(editButton).toBeInTheDocument();

            fireEvent.click(editButton);
        });

        expect(pushMock).toHaveBeenCalledWith('/Admin/UpdatePatientPage?email=john@example.com');
    });
});