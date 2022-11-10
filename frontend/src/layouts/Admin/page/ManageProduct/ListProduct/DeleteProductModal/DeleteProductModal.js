import classNames from 'classnames';
import { useState } from 'react';
import { toast } from 'react-toastify';
import { deleteProductByProductId } from '~/services/admin/productService';

const DeleteProductModal = ({ show, setShow, data, setData, fetchListProduct }) => {
  const [isSending, setIsSending] = useState(false);

  const handleClickDeleteProduct = async () => {
    setIsSending(true);
    const response = await deleteProductByProductId(data.productId);
    if (response && +response.code === 0) {
      setShow(false);
      setData({});
      toast.success('Delete product successfully.');
      fetchListProduct(0);
    } else {
      toast.error(response?.message);
    }
    setIsSending(false);
  };

  return (
    <div
      className={classNames(
        { flex: show },
        { hidden: !show },
        'delete-product-warning-modal',
        'bg-slate-900/[0.5] overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 bottom-0 z-50 w-full md:inset-0 md:h-full justify-center items-center',
      )}
    >
      <div className="relative p-4 w-full max-w-2xl h-auto">
        <div className="relative bg-white rounded-lg shadow">
          <div className="flex justify-between items-start px-4 py-3 rounded-t border-b">
            <h3 className="text-xl font-semibold text-orange-600">Warning</h3>
            <button
              className="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
              onClick={() => {
                setShow(false);
                setData({});
              }}
            >
              <svg
                aria-hidden="true"
                className="w-5 h-5"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fillRule="evenodd"
                  d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                  clipRule="evenodd"
                ></path>
              </svg>
            </button>
          </div>
          <div className="px-4 py-2 flex flex-col">
            <div className="my-4 text-red-600">
              <div>
                Are you sure to delete product "<span className="font-semibold">{data?.name}</span>"? Any deletion
                cannot be reverted.
              </div>
            </div>
          </div>
          <div className="flex items-center justify-between px-4 py-2 space-x-2 rounded-b border-t border-gray-200">
            <button
              className="w-1/4 my-2 mb-2 font-medium text-white bg-black hover:bg-slate-900 py-3"
              onClick={() => {
                setShow(false);
                setData({});
              }}
            >
              Cancel
            </button>
            <button
              className="w-1/4 my-2 mb-2 font-medium text-white bg-red-600 hover:bg-red-700 py-3"
              onClick={() => handleClickDeleteProduct()}
              disabled={isSending}
            >
              Delete
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DeleteProductModal;
