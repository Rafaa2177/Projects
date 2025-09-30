import React from 'react';
import '@testing-library/jest-dom';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import ListStaffPage from '../../../src/pages/Admin/ListStaffPage';
import { useRouter } from 'next/router';

// Mock the useRouter hook
jest.mock('next/router', () => ({
    useRouter: jest.fn(),
}));

// Mock window.confirm
beforeAll(() => {
    global.confirm = jest.fn(() => true);  // Always return true for confirmation
});

describe('ListStaffPage', () => {
    it('should render without crashing', () => {
        useRouter.mockImplementation(() => ({
            push: jest.fn(),
            query: {},
        }));

        render(<ListStaffPage />);
        expect(screen.getByText('List Staffs')).toBeInTheDocument();
    });

    test('fetches and displays all staff members on "List All Staffs" button click', async () => {
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

        // Mock the API fetch
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockStaffData),
            })
        );

        render(<ListStaffPage />);

        const listAllButton = screen.getByRole('button', { name: 'List All Staffs' });
        fireEvent.click(listAllButton);

        // Wait for fetch to be called and data to be rendered
        expect(global.fetch).toHaveBeenCalledWith('https://localhost:5001/api/Staffs/active');

        await waitFor(() => {
            expect(screen.getByText('John Doe')).toBeInTheDocument();
            expect(screen.getByText('12345')).toBeInTheDocument();
            expect(screen.getByText('Cardiology')).toBeInTheDocument();  // Assuming the specialization should be shown
        });
    });

    test('deletes a staff member on "Delete" button click', async () => {
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

        global.fetch = jest.fn((url, options) => {
            if (options?.method === 'DELETE') {
                expect(url).toBe('https://localhost:5001/api/staffs/12345');
                return Promise.resolve({ ok: true });
            }
            return Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockStaffData),
            });
        });

        render(<ListStaffPage />);

        const listAllButton = screen.getByRole('button', { name: 'List All Staffs' });
        fireEvent.click(listAllButton);

        await waitFor(() => {
            const deleteButton = screen.getByRole('button', { name: 'Delete' });
            fireEvent.click(deleteButton);
        });

        expect(global.fetch).toHaveBeenCalledWith(
            'https://localhost:5001/api/staffs/12345',
            expect.objectContaining({ method: 'DELETE' })
        );

        // Ensure the staff is removed from the list after delete
        await waitFor(() => {
            expect(screen.queryByText('John Doe')).not.toBeInTheDocument();
        });
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

        render(<ListStaffPage />);

        const listAllButton = screen.getByRole('button', { name: 'List All Staffs' });
        fireEvent.click(listAllButton);

        await waitFor(() => {
            const editButton = screen.getByRole('button', { name: 'Edit' });
            expect(editButton).toBeInTheDocument();

            fireEvent.click(editButton);
        });

        expect(pushMock).toHaveBeenCalledWith('/Admin/UpdateStaffPage?email=john@example.com');
    });

    test('searches staff by email using the form', async () => {
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

        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockStaffData),
            })
        );

        render(<ListStaffPage />);

        const selectFilter = screen.getByRole('combobox');
        fireEvent.change(selectFilter, { target: { value: 'email' } });

        const inputField = screen.getByPlaceholderText('Search by email');
        fireEvent.change(inputField, { target: { value: 'john@example.com' } });

        const searchButton = screen.getByRole('button', { name: 'Search' });
        fireEvent.click(searchButton);

        expect(global.fetch).toHaveBeenCalledWith(
            'https://localhost:5001/api/Staffs/email/john@example.com'
        );

        await waitFor(() => {
            expect(screen.getByText('John Doe')).toBeInTheDocument();
        });
    });
});
