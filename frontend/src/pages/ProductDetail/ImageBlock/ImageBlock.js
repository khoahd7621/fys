import { useEffect, useRef, useState } from 'react';
import Slider from 'react-slick';

import { RiArrowRightSLine, RiArrowLeftSLine } from 'react-icons/ri';

import './ImageBlock.scss';

import image1 from '~/assets/images/product/slider/slider-1.webp';
import image2 from '~/assets/images/product/slider/slider-2.webp';
import image3 from '~/assets/images/product/slider/slider-3.webp';
import image4 from '~/assets/images/product/slider/slider-4.webp';
import image5 from '~/assets/images/product/slider/slider-5.webp';

const ImageBlock = () => {
  const [nav1, setNav1] = useState(null);
  const [nav2, setNav2] = useState(null);
  const slider1 = useRef(null);
  const slider2 = useRef(null);

  const images = [
    { id: 1, src: image1 },
    { id: 2, src: image2 },
    { id: 3, src: image3 },
    { id: 4, src: image4 },
    { id: 5, src: image5 },
  ];

  useEffect(() => {
    setNav1(slider1.current);
    setNav2(slider2.current);
  }, []);

  const settingsSlider2 = {
    slidesToShow: 4,
    focusOnSelect: true,
    swipeToSlide: true,
    infinite: false,
    dots: false,
    nextArrow: <CustomArrow next />,
    prevArrow: <CustomArrow prev />,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 4,
          arrows: false,
        },
      },
      {
        breakpoint: 640,
        settings: {
          slidesToShow: 3,
          arrows: false,
          dots: true,
        },
      },
    ],
  };

  return (
    <div className="product-detail-image-block">
      <div className="slider-big-image">
        <Slider asNavFor={nav2} ref={slider1} infinite={false} arrows={false}>
          {images.map((image) => (
            <div key={`image-up-${image.id}`}>
              <img src={image.src} alt="preview" />
            </div>
          ))}
        </Slider>
      </div>
      <div className="slider-small-image">
        <Slider {...settingsSlider2} asNavFor={nav1} ref={slider2}>
          {images.map((image) => (
            <img key={`image-down-${image.id}`} src={image.src} alt="preview" />
          ))}
        </Slider>
      </div>
    </div>
  );
};

const CustomArrow = (props) => {
  const { prev, next, className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', color: '#000' }} onClick={onClick}>
      {next && <RiArrowRightSLine />}
      {prev && <RiArrowLeftSLine />}
    </div>
  );
};

export default ImageBlock;
