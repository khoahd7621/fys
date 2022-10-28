import { useEffect, useState } from 'react';
import { useImmer } from 'use-immer';
import { v4 as uuidv4 } from 'uuid';
import { toast } from 'react-toastify';
import _ from 'lodash';

import MainImage from './MainImage/MainImage';
import CustomSelect from './CustomSelect/CustomSelect';
import SelectColor from './SelectColor/SelectColor';
import Validation from '~/utils/validation';

const CreateNewProduct = () => {
  const sizeOptions = [
    { value: '0', label: 'S' },
    { value: '1', label: 'M' },
    { value: '2', label: 'L' },
  ];
  const isVisibleOptions = [
    { value: false, label: 'Yes' },
    { value: true, label: 'No' },
  ];
  const initListColors = [
    {
      id: uuidv4(),
      name: '',
      images: [],
    },
  ];
  const [primaryImage, setPrimaryImage] = useImmer({
    file: '',
    previewImage: '',
  });
  const [secondaryImage, setSecondaryImage] = useImmer({
    file: '',
    previewImage: '',
  });
  const [listColors, setListColors] = useImmer(initListColors);
  const [listSizes, setSizes] = useState([]);
  const [product, setProduct] = useImmer({
    productName: '',
    productPrice: '',
    productDescription: '',
    isVisible: '',
  });

  useEffect(() => {
    // Todo: Call API fetch list sizes
  }, []);

  const handleChangeSelectSize = (e) => {
    setSizes([...e]);
  };

  const handleAddNewColor = () => {
    setListColors((draft) => {
      draft.push({
        id: uuidv4(),
        name: '',
        images: [],
      });
    });
  };

  const handleOnChangeInput = (event) => {
    setProduct((draft) => {
      draft[event.target.name] = event.target.value;
    });
  };

  const handleChangeIsVisibleSelect = (event) => {
    setProduct((draft) => {
      draft.isVisible = event.value;
    });
  };

  const validateData = () => {
    if (Validation.isEmpty(product.productName)) {
      toast.error('Product name is required');
      return false;
    }
    if (Validation.isEmpty(product.productPrice)) {
      toast.error('Product price is required');
      return false;
    }
    if (!Validation.isIntegerNumber(product.productPrice) || !Validation.isHigherThanZero(product.productPrice)) {
      toast.error('Invalid price');
      return false;
    }
    if (Validation.isEmpty(product.productDescription)) {
      toast.error('Product description is required');
      return false;
    }
    if (Validation.isEmpty(product.isVisible)) {
      toast.error('Product visible is required');
      return false;
    }
    if (_.isEmpty(listSizes)) {
      toast.error('Product size is required');
      return false;
    }
    if (_.isEmpty(primaryImage.previewImage)) {
      toast.error('Primary cover image is required');
      return false;
    }
    if (_.isEmpty(secondaryImage.previewImage)) {
      toast.error('Secondary cover image is required');
      return false;
    }
    for (let i = 0; i < listColors.length; i++) {
      if (_.isEmpty(listColors[i].name)) {
        toast.error('Please select color for Color ' + (i + 1));
        return false;
      }
      if (listColors[i].images.length <= 0) {
        toast.error('Please add at least 1 image for Color ' + (i + 1));
        return false;
      }
    }
    return true;
  };

  const handleSubmitCreateNewProduct = () => {
    const isValid = validateData();
    if (!isValid) {
      toast.error('Please fill all required fields before submitting.');
    } else {
      alert('Call api');
    }
  };

  return (
    <div className="h-[calc(100vh-193px)] overflow-y-auto">
      <div className="content">
        <div className="border rounded-md p-3 mb-6">
          <div className="grid grid-cols-3">
            <div className="col-span-1">
              <div className="p-4 flex flex-col">
                <div className="mb-4">
                  <label htmlFor="product-name" className="block mb-2 text-base font-medium text-gray-900 ">
                    Product name (<span className="text-red-600">*</span>):
                  </label>
                  <input
                    type="text"
                    id="product-name"
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                    placeholder="Enter product name"
                    name="productName"
                    value={product.productName}
                    onChange={(event) => handleOnChangeInput(event)}
                  />
                </div>
                <div className="mb-4">
                  <label htmlFor="product-price" className="block mb-2 text-base font-medium text-gray-900 ">
                    Price (<span className="text-red-600">*</span>):
                  </label>
                  <input
                    type="text"
                    id="product-price"
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                    placeholder="Enter price"
                    name="productPrice"
                    value={product.productPrice}
                    onChange={(event) => handleOnChangeInput(event)}
                  />
                </div>
                <div className="mb-4">
                  <label htmlFor="product-description" className="block mb-2 text-base font-medium text-gray-900 ">
                    Description (<span className="text-red-600">*</span>):
                  </label>
                  <input
                    type="text"
                    id="product-description"
                    className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                    placeholder="Enter description"
                    name="productDescription"
                    value={product.productDescription}
                    onChange={(event) => handleOnChangeInput(event)}
                  />
                </div>
                <div className="mb-4">
                  <label className="block mb-2 text-base font-medium text-gray-900 ">
                    Show product after created (<span className="text-red-600">*</span>):
                  </label>
                  <CustomSelect
                    options={isVisibleOptions}
                    onChange={handleChangeIsVisibleSelect}
                    placeholder={'Select visible...'}
                  />
                </div>
                <div>
                  <label className="block mb-2 text-base font-medium text-gray-900 ">
                    Select size (<span className="text-red-600">*</span>):
                  </label>
                  <CustomSelect
                    isMulti
                    options={sizeOptions}
                    onChange={handleChangeSelectSize}
                    placeholder={'Select size...'}
                  />
                </div>
              </div>
            </div>
            <div className="col-span-2">
              <div className="p-4 h-full">
                <div className="flex justify-end mb-4">
                  <button
                    className="bg-blue-600 text-white rounded-lg px-3 py-2"
                    onClick={() => handleSubmitCreateNewProduct()}
                  >
                    Create new product
                  </button>
                </div>
                <div className="flex gap-8">
                  {/* Primary image */}
                  <div className="w-1/2">
                    <MainImage title={'Primary cover image'} image={primaryImage} setImage={setPrimaryImage} />
                  </div>
                  {/* Secondary image */}
                  <div className="w-1/2">
                    <MainImage title={'Secondary cover image'} image={secondaryImage} setImage={setSecondaryImage} />
                  </div>
                </div>
              </div>
            </div>
            <div className="col-span-3 px-4">
              <label className="block mb-2 text-base font-medium text-gray-900 ">
                Product colors (<span className="text-red-600">*</span>):
              </label>
              <SelectColor listColors={listColors} setListColors={setListColors} />
              <button
                className="rounded-md bg-yellow-500 hover:bg-yellow-600 text-white px-6 py-2"
                onClick={() => handleAddNewColor()}
              >
                Add color
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CreateNewProduct;
