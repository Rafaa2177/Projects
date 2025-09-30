import '@testing-library/jest-dom'
import DeleteOperationRequestButton from "../../../src/components/OperationRequests/DeleteOperationRequestButton";
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

describe('DeleteOperationRequestButton', () => {
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
        render(<DeleteOperationRequestButton id="123" />);
        const button = screen.getByRole('button', { name: /apagar/i });
        expect(button).toBeInTheDocument();
    });

    it('calls handleRemove when button is clicked and confirms deletion', async () => {
        const confirmMock = jest.spyOn(window, 'confirm').mockReturnValue(true);
        const alertMock = jest.spyOn(window, 'alert').mockImplementation(() => {});
        global.fetch = jest.fn().mockResolvedValue({ ok: true });

        jest.spyOn(Storage.prototype, 'getItem').mockImplementation((key) => {
            if (key === 'docId') return 'testDocId';
            if (key === 'accessToken') return 'testAccessToken';
            return null;
        });

        render(<DeleteOperationRequestButton id="123" />);
        const button = screen.getByRole('button', { name: /apagar/i });

        await userEvent.click(button);

        expect(confirmMock).toHaveBeenCalledWith(
            'are you sure you want to delete? Request id:123'
        );

        expect(fetch).toHaveBeenCalledWith(
            `https://localhost:5001/api/OperationRequest/testDocId/testAccessToken/123`,
            { method: 'DELETE' }
        );

        expect(alertMock).toHaveBeenCalledWith('Request deleted successfully');
        expect(window.location.reload).toHaveBeenCalled();
    });

    it('does not delete when confirmation is declined', async () => {
        const confirmMock = jest.spyOn(window, 'confirm').mockReturnValue(false);

        render(<DeleteOperationRequestButton id="123" />);
        const button = screen.getByRole('button', { name: /apagar/i });

        await userEvent.click(button);

        expect(confirmMock).toHaveBeenCalled();
        expect(global.fetch).not.toHaveBeenCalled();
    });
});
