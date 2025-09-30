import React from "react";

const UpdateOperationRequestButton = ({ id }: { id: string }) => {
    function handleRemove(){
        const shouldRemove = confirm("are you sure you want to delete?")

        if (shouldRemove) {

        }
    }
    return (
        <button
            className="bg-pink-400 text-white py-2 px-5 rounded-lg float-right -mt-8"
            onClick={() => handleRemove()}>
            Editar
        </button>
    )


};
export default UpdateOperationRequestButton;
