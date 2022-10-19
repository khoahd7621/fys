import classNames from 'classnames';
import CustomProduct from './CustomProduct';
import Table from './Table';

const OrderDetail = () => {
  const columns = [
    {
      Header: 'Product',
      accessor: 'product',
      Cell: CustomProduct,
    },
    {
      Header: 'Price',
      accessor: 'price',
    },
    {
      Header: 'Quantity',
      accessor: 'quantity',
    },
    {
      Header: 'Total',
      accessor: 'total',
    },
  ];

  const data = [
    {
      product: {
        image:
          'https://bizweb.dktcdn.net/thumb/1024x1024/100/331/067/products/z2300839217494-fdcfe88d8971ac5d8bc50eb7c4788784.jpg?v=1613495212347',
        name: 'VGS LOGO FUTURISTIC TEE',
        size: 'XL',
        color: 'Black',
        code: 'VGS-#123456',
      },
      price: '740.000₫',
      quantity: '1',
      total: '740.000₫',
    },
  ];

  return (
    <div className="order-detail">
      <div className="title">
        <div className="block lg:flex justify-between">
          <h2 className="text-xl uppercase mb-5 lg:mb-7">Order details #WEB1234</h2>
          <p className="order-date mb-4 lg:mb-0">Order date: 15/01/2020</p>
        </div>
      </div>
      <div className="content">
        <div className="status">
          <div className="flex gap-6">
            <p>
              Payment status: <span className={classNames('font-bold italic text-blue-700')}>Paid</span>
            </p>
            <p>
              Shipping status: <span className={classNames('font-bold italic text-blue-700')}>Unship</span>
            </p>
          </div>
        </div>
        <div className="infor">
          <div className="grid grid-cols-12 gap-4">
            <div className="col-span-12 lg:col-span-6">
              <div className="address">
                <div className="title">
                  <h3 className="mt-5 mb-2 uppercase">Delivery address</h3>
                </div>
                <div className="border border-slate-300 rounded-md pt-3 px-6 pb-3">
                  <h3 className="name text-base font-bold">Hoang Dang Khoa</h3>
                  <p className="text-sm">
                    Address: 33 Đường số 12B, Chân Phúc Cẩm, Long Thạnh Mỹ, Quận 9, TP Hồ Chí Minh
                  </p>
                  <p className="text-sm">Phone number: 0792596763</p>
                </div>
              </div>
            </div>
            <div className="col-span-12 lg:col-span-3">
              <div className="payment">
                <div className="title">
                  <h3 className="mt-5 mb-2 uppercase">Payment</h3>
                </div>
                <div className="border border-slate-300 rounded-md pt-3 px-6 pb-3">
                  <p className="text-sm">Cash on delivery (COD)</p>
                </div>
              </div>
            </div>
            <div className="col-span-12 lg:col-span-3">
              <div className="note">
                <div className="title">
                  <h3 className="mt-5 mb-2 uppercase">Note</h3>
                </div>
                <div className="border border-slate-300 rounded-md pt-3 px-6 pb-3">
                  <p className="text-sm">N/a</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="table-products">
          <div className="mt-4">
            <Table columns={columns} data={data} />
          </div>
        </div>
        <div className="total-order">
          <table className="w-full mt-4 table-fixed">
            <tbody>
              <tr>
                <td className="border border-slate-300 px-2 py-2">Transportation cost</td>
                <td className="border border-slate-300 px-2 py-2">40.000đ</td>
              </tr>
              <tr>
                <td className="border border-slate-300 px-2 py-2">Total money</td>
                <td className="border border-slate-300 px-2 py-2">740.000đ</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default OrderDetail;
