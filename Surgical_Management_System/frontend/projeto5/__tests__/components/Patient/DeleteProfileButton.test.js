import '@testing-library/jest-dom';
import DeleteProfileButton from "../../../src/components/Pacient/DeleteProfileButton";
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { useRouter } from 'next/navigation';


jest.mock('next/navigation', () => ({
    useRouter: jest.fn(),
}));

describe('DeleteProfileButton', () => {
    beforeEach(() => {
        jest.clearAllMocks();

        // Mock seguro para window.location.reload
        Object.defineProperty(window, 'location', {
            configurable: true,
            value: {
                ...window.location,
                reload: jest.fn(),
            },
        });
    });

    it('renders the button', () => {
        render(<DeleteProfileButton />);
        const button = screen.getByRole('button', { name: /delete account/i });
        expect(button).toBeInTheDocument();
    });

    it('calls handleRemove when button is clicked and confirms deletion', async () => {
        const confirmMock = jest.spyOn(window, 'confirm').mockReturnValue(true);
        const alertMock = jest.spyOn(window, 'alert').mockImplementation(() => {});
        const routerPushMock = jest.fn();

        useRouter.mockReturnValue({
            push: routerPushMock,
        });

        global.fetch = jest.fn().mockResolvedValue({ ok: true });

        jest.spyOn(Storage.prototype, 'getItem').mockImplementation((key) => {
            if (key === 'accessToken') return 'testAccessToken';
            return null;
        });

        render(<DeleteProfileButton />);
        const button = screen.getByRole('button', { name: /delete account/i });

        await userEvent.click(button);

        // Verificações
        expect(confirmMock).toHaveBeenCalledWith('Are you sure you want to delete?');
        expect(fetch).toHaveBeenCalledWith(
            'https://localhost:5001/api/Patients/deletePatientAccount/testAccessToken',
            { method: 'DELETE' }
        );
        expect(alertMock).toHaveBeenCalledWith('Account deleted successfully');
        expect(routerPushMock).toHaveBeenCalledWith('http://localhost:3000');
    });
});

    it('shows an error alert when deletion fails', async () => {
        const confirmMock = jest.spyOn(window, 'confirm').mockReturnValue(true);
        const alertMock = jest.spyOn(window, 'alert').mockImplementation(() => {});

        // Mock do fetch com erro
        global.fetch = jest.fn().mockResolvedValue({
            ok: false,
            statusText: 'Server error',
            text: jest.fn().mockResolvedValue('Detailed error message'),
        });

        // Mock do localStorage
        jest.spyOn(Storage.prototype, 'getItem').mockImplementation((key) => {
            if (key === 'accessToken') return 'testAccessToken';
            return null;
        });

        render(<DeleteProfileButton />);
        const button = screen.getByRole('button', { name: /delete account/i });

        await userEvent.click(button);

        expect(confirmMock).toHaveBeenCalledWith('Are you sure you want to delete?');
        expect(fetch).toHaveBeenCalledWith(
            'https://localhost:5001/api/Patients/deletePatientAccount/testAccessToken',
            { method: 'DELETE' }
        );
        expect(alertMock).toHaveBeenCalledWith(
            'Error deleting request: Detailed error message'
        );
    });

    it('does not delete when confirmation is declined', async () => {
        const confirmMock = jest.spyOn(window, 'confirm').mockReturnValue(false);

        render(<DeleteProfileButton />);
        const button = screen.getByRole('button', { name: /delete account/i });

        await userEvent.click(button);

        expect(confirmMock).toHaveBeenCalled();
        expect(global.fetch).not.toHaveBeenCalled();
    });

    it('shows an alert if no access token is found', async () => {
        const confirmMock = jest.spyOn(window, 'confirm').mockReturnValue(true);
        const alertMock = jest.spyOn(window, 'alert').mockImplementation(() => {});

        // Mock do localStorage sem token
        jest.spyOn(Storage.prototype, 'getItem').mockImplementation(() => null);

        render(<DeleteProfileButton />);
        const button = screen.getByRole('button', { name: /delete account/i });

        await userEvent.click(button);

        expect(confirmMock).toHaveBeenCalledWith('Are you sure you want to delete?');
        expect(global.fetch).not.toHaveBeenCalled();
        expect(alertMock).toHaveBeenCalledWith('No access token found.');
    });
