import React from 'react';
import styles from '../../pages/SignupPage.module.css'
const CheckboxInput = ({ checked, onChange, label }) => {
  return (
    <div className={styles.inputOptional}>
      <input
        type="checkbox"
        checked={checked}
        onChange={onChange}
      />
      <div>{label}</div>
    </div>
  );
};

export default CheckboxInput;
