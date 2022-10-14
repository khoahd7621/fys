import { BsTruck } from 'react-icons/bs';
import { GiCardboardBox } from 'react-icons/gi';
import { FiPhoneCall } from 'react-icons/fi';
import { BiStoreAlt } from 'react-icons/bi';

import Carousel from './Carousel/Carousel';
import Service from './Service/Service';

const Home = () => {
  return (
    <div className="container mx-auto max-w-[730px] lg:max-w-[970px] xl:max-w-[1150px] px-3">
      <div className="home-slider">
        <Carousel />
      </div>
      <div className="home-service">
        <div className="grid grid-cols-1 md:grid-cols-4">
          <Service link="#" Icon={BsTruck} title="Free ship" desc="With bill from 500,000 VND" />
          <Service
            link="#"
            Icon={GiCardboardBox}
            title="7 days product exchange"
            desc="Exchange products within 7 days"
          />
          <Service
            link="#"
            Icon={FiPhoneCall}
            title="Purchase (09h00 - 22h00, T2 - CN)"
            desc="Hotline for purchase 1800 1234"
          />
          <Service url="#" Icon={BiStoreAlt} title="Shop system" desc="1 store in the whole system" />
        </div>
      </div>
    </div>
  );
};

export default Home;
