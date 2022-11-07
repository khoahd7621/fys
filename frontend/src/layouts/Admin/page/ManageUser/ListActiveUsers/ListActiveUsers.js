import { useEffect, useState } from 'react';
import ReactPaginate from 'react-paginate';
import { toast } from 'react-toastify';
import { getListUserByRoleAndStatusWithPaginate } from '~/services/admin/userService';
import BlockWarningModal from './BlockWarningModal';

const ListActiveUsers = () => {
  const STATUS = 'ACTIVE';
  const ROLE = 'USER';
  const LIMIT_ACCOUNT = 10;

  const [listUsers, setListUsers] = useState([]);
  const [pageCount, setPageCount] = useState(1);
  const [currentPage, setCurrentPage] = useState(1);
  const [showBlockWarningModal, setShowBlockWarningModal] = useState(false);
  const [dataBlockWarning, setDataBlockWarning] = useState({});

  useEffect(() => {
    fetchListActiveUsersWithPaginate(currentPage - 1);
  }, []);

  const fetchListActiveUsersWithPaginate = async (page) => {
    const response = await getListUserByRoleAndStatusWithPaginate(ROLE, STATUS, page, LIMIT_ACCOUNT);
    if (response && +response.code === 0) {
      setListUsers(response?.data?.listUsers);
      setPageCount(response?.data?.totalPages);
    } else {
      toast.error(response?.message);
    }
  };

  const handlePageClick = (event) => {
    setCurrentPage(+event.selected + 1);
    fetchListActiveUsersWithPaginate(+event.selected);
  };

  const handleShowBlockWarningModal = (user) => {
    setShowBlockWarningModal(true);
    setDataBlockWarning(user);
  };

  return (
    <div className="h-[calc(100vh-129px)] overflow-y-auto">
      <div className="content">
        <div className="border rounded-md p-3 mb-6">
          <div className="table">
            <div className="overflow-x-auto">
              <table className="table-fixed w-full">
                <thead className="bg-slate-300">
                  <tr>
                    <th
                      scope="col"
                      className="px-4 pt-4 pb-2 border border-gray-200 text-left text-sm font-bold text-gray-700 uppercase tracking-wider"
                    >
                      Id
                    </th>
                    <th
                      scope="col"
                      className="px-4 pt-4 pb-2 border border-gray-200 text-left text-sm font-bold text-gray-700 uppercase tracking-wider"
                    >
                      Full Name
                    </th>
                    <th
                      scope="col"
                      className="px-4 pt-4 pb-2 border border-gray-200 text-left text-sm font-bold text-gray-700 uppercase tracking-wider"
                    >
                      Email
                    </th>
                    <th
                      scope="col"
                      className="px-4 pt-4 pb-2 border border-gray-200 text-left text-sm font-bold text-gray-700 uppercase tracking-wider"
                    >
                      Phone
                    </th>
                    <th
                      scope="col"
                      className="px-4 pt-4 pb-2 border border-gray-200 text-left text-sm font-bold text-gray-700 uppercase tracking-wider"
                    >
                      Address
                    </th>
                    <th
                      scope="col"
                      className="px-4 pt-4 pb-2 border border-gray-200 text-left text-sm font-bold text-gray-700 uppercase tracking-wider"
                    >
                      Status
                    </th>
                    <th
                      scope="col"
                      className="px-4 pt-4 pb-2 border border-gray-200 text-left text-sm font-bold text-gray-700 uppercase tracking-wider"
                    ></th>
                  </tr>
                </thead>
                <tbody>
                  {listUsers &&
                    listUsers.length > 0 &&
                    listUsers.map((user, index) => (
                      <tr key={`users-${user.id}-${index}`}>
                        <td className="px-4 py-2 text-sm border border-gray-200">{user.id}</td>
                        <td className="px-4 py-2 text-sm border border-gray-200">
                          {`${user.firstName} ${user.lastName}`}
                        </td>
                        <td className="px-4 py-2 text-sm border border-gray-200">{user.email}</td>
                        <td className="px-4 py-2 text-sm border border-gray-200">{user.phone}</td>
                        <td className="px-4 py-2 text-sm border border-gray-200">
                          {user?.address ? user.address : 'N/a'}
                        </td>
                        <td className="px-4 py-2 text-sm border border-gray-200">
                          {user?.status === 'ACTIVE' ? (
                            <span className="text-green-600 font-semibold">{user?.status}</span>
                          ) : (
                            <span className="text-red-600 font-semibold">{user?.status}</span>
                          )}
                        </td>
                        <td className="px-4 py-2 text-sm border border-gray-200">
                          <button
                            className="bg-red-600 text-white px-4 py-1 hover:bg-red-800"
                            onClick={() => handleShowBlockWarningModal(user)}
                          >
                            Block
                          </button>
                        </td>
                      </tr>
                    ))}
                </tbody>
              </table>
            </div>
          </div>
          <div className="paginate">
            {pageCount > 1 && (
              <ReactPaginate
                marginPagesDisplayed={3}
                onPageChange={handlePageClick}
                pageRangeDisplayed={5}
                pageCount={pageCount}
                breakLabel="..."
                nextLabel="Next"
                previousLabel="Previous"
                renderOnZeroPageCount={null}
                pageLinkClassName="py-2 px-3 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 select-none"
                pageClassName="py-2"
                breakLinkClassName="py-2 px-3"
                previousLinkClassName="py-2 px-3 ml-0 leading-tight text-gray-500 bg-white rounded-l-lg border border-gray-300 hover:bg-gray-100 hover:text-gray-700 select-none"
                previousClassName="py-2"
                nextLinkClassName="py-2 px-3 leading-tight text-gray-500 bg-white rounded-r-lg border border-gray-300 hover:bg-gray-100 hover:text-gray-700 select-none"
                nextClassName="py-2"
                containerClassName="flex justify-center mt-6 mb-4"
                activeLinkClassName="text-blue-600 bg-blue-50"
              />
            )}
          </div>
        </div>
      </div>
      <BlockWarningModal
        show={showBlockWarningModal}
        setShow={setShowBlockWarningModal}
        data={dataBlockWarning}
        setData={setDataBlockWarning}
        fetchListUser={fetchListActiveUsersWithPaginate}
      />
    </div>
  );
};

export default ListActiveUsers;
