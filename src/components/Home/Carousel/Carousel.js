import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './Carousel.scss';

import slider1 from '~/assets/carousel/slider1.png';
import slider2 from '~/assets/carousel/slider2.png';
import slider3 from '~/assets/carousel/slider3.png';
import { Link } from 'react-router-dom';

const Carousel = () => {
  const settings = {
    dots: true,
    fade: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 5000,
    customPaging: () => <div className="custom-dot"></div>,
  };
  return (
    <Slider {...settings}>
      <Link to={'/collection/all'}>
        <img src={slider1} alt="carousel" title="Product" />
      </Link>
      <Link to={'/collection/all'}>
        <img src={slider2} alt="carousel" title="Product" />
      </Link>
      <Link to={'/collection/all'}>
        <img src={slider3} alt="carousel" title="Product" />
      </Link>
    </Slider>
  );
};

export default Carousel;
