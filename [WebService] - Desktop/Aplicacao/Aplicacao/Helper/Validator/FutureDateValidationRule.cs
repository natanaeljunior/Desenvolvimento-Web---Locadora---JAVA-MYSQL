﻿using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Aplicacao.Validator
{
    public class FutureDateValidationRule : ValidationRule
    {
        public override ValidationResult Validate(object value, CultureInfo cultureInfo)
        {
            DateTime time;

            if (!DateTime.TryParse((value ?? "").ToString(), CultureInfo.CurrentCulture,
                DateTimeStyles.AssumeLocal | DateTimeStyles.AllowWhiteSpaces, out time))
                return new ValidationResult(false, "Data inválida");

            return time.Date <= DateTime.Now.Date
                ? new ValidationResult(false, "Data futura obrigatória")
                : ValidationResult.ValidResult;
        }
    }
}
