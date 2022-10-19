import CustomStatus from '../OrderTable/CustomStatus';
import OrderTable from '../OrderTable/OrderTable';

const Order = () => {
  const columns = [
    {
      Header: 'Order',
      accessor: 'order',
    },
    {
      Header: 'Date',
      accessor: 'date',
    },
    {
      Header: 'Address',
      accessor: 'address',
    },
    {
      Header: 'Order value',
      accessor: 'orderValue',
    },
    {
      Header: 'Payment status',
      accessor: 'paymentStatus',
      Cell: CustomStatus,
    },
    {
      Header: 'Transport status',
      accessor: 'transportStatus',
      Cell: CustomStatus,
    },
  ];
  const data = [
    {
      order: 'WEB14267',
      date: '15/01/2020',
      address: 'Chân Phúc Cẩm, Long Thạnh Mỹ, Quận 9, TP Hồ Chí Minh, Vietnam',
      orderValue: '740.000₫',
      paymentStatus: 'Paid',
      transportStatus: 'Shipped',
    },
    {
      order: 'WEB14267',
      date: '15/01/2020',
      address: 'Chân Phúc Cẩm, Long Thạnh Mỹ, Quận 9, TP Hồ Chí Minh, Vietnam',
      orderValue: '740.000₫',
      paymentStatus: 'Unpay',
      transportStatus: 'Unship',
    },
    {
      order: 'WEB14267',
      date: '15/01/2020',
      address: 'Chân Phúc Cẩm, Long Thạnh Mỹ, Quận 9, TP Hồ Chí Minh, Vietnam',
      orderValue: '740.000₫',
      paymentStatus: 'Cancelled',
      transportStatus: 'Cancelled',
    },
  ];

  return (
    <div className="order-detail">
      <div className="title">
        <h2 className="text-xl uppercase mb-7">Your order</h2>
      </div>
      <div className="content">
        <OrderTable columns={columns} data={data} />
      </div>
    </div>
  );
};

export default Order;
